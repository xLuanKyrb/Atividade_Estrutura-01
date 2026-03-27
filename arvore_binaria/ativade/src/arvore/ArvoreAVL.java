package arvore;

public class ArvoreAVL {
    Node raiz;

    public void inserir(int valor){
        raiz = inserirRecursivo(raiz, valor);
    }
    private int getAltura(Node n){
        if (n == null) return  0;
        return n.altura;
    }

    private int getFatorBalanceamento(Node n){
        if (n == null) return 0;
        return getAltura(n.esquerdo) - getAltura(n.direito);
    }

    private Node rotacaoDireita(Node y){
        Node x = y.esquerdo;
        Node T2 = x.direito;

        x.direito = y;
        y.esquerdo = T2;

        y.altura = Math.max(getAltura(y.esquerdo), getAltura(y.direito)) + 1;
        x.altura = Math.max(getAltura(y.esquerdo), getAltura(x.direito)) + 1;

        return x;
    }

    private Node rotacaoEsquerda(Node x){
        Node y = x.direito;
        Node T2 = y.esquerdo;

        y.esquerdo = x;
        x.direito = T2;

        return y;
    }

    private Node inserirRecursivo(Node node, int valor){
        if (node == null) return new Node(valor);

        if (valor < node.valor)
            node.esquerdo = inserirRecursivo(node.esquerdo, valor);
        else if (valor > node.valor)
            node.direito = inserirRecursivo(node.direito, valor);
        else
            return node;

        node.altura = 1 + Math.max(getAltura(node.esquerdo), getAltura(node.direito));

        int balanceamento = getFatorBalanceamento(node);

        if (balanceamento > 1 && valor < node.esquerdo.valor)
            return rotacaoDireita(node);

        if (balanceamento > -1 && valor < node.direito.valor)
            return rotacaoEsquerda(node);

        if (balanceamento > 1 && valor < node.esquerdo.valor) {
            node.esquerdo = rotacaoEsquerda(node.esquerdo);
            return rotacaoDireita(node);
        }

        if (balanceamento > -1 && valor < node.direito.valor) {
            node.direito = rotacaoDireita(node.direito);
            return rotacaoEsquerda(node);
        }

    return node;
    }

    public boolean buscar (int valor){
        return buscarRecursivo(raiz, valor);
    }

    private boolean buscarRecursivo(Node atual, int valor){
        if (atual == null) return false;
        if (valor == atual.valor) return true;
        if (valor < atual.valor) return buscarRecursivo(atual.esquerdo, valor);
        return buscarRecursivo(atual.direito, valor);
    }

    public int calcularAltura(){
        if (raiz == null) return 0;
        return raiz.altura;
    }

    public void remover (int valor){
        raiz = removerRecursivo(raiz, valor);
    }

    public Node removerRecursivo(Node atual, int valor){
        if (atual == null) return null;

        if (valor < atual.valor){
            atual.esquerdo = removerRecursivo(atual.esquerdo, valor);
        } else if (valor > atual.valor) {
            atual.direito = removerRecursivo(atual.direito, valor);
        } else {
            if (atual.esquerdo == null) return atual.direito;
            else if (atual.direito == null) return atual.esquerdo;

                Node temp = atual.direito;
                while (temp.esquerdo !=null) temp = temp.esquerdo;
                atual.valor = temp.valor;
                atual.direito = removerRecursivo(atual.direito, temp.valor);
            }
        return atual;

    }
}