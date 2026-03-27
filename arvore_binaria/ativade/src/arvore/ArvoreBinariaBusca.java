package arvore;

public class ArvoreBinariaBusca {
    Node raiz;

    public void inserir(int valor){
        raiz = inserirRescursivo(raiz, valor);
    }

    private Node inserirRescursivo(Node atual, int valor){
        if (atual == null){
            return new Node(valor);
        }
        if (valor < atual.valor){
            atual.esquerdo = inserirRescursivo(atual.esquerdo, valor);
        } else if (valor > atual.valor) {
            atual.direito = inserirRescursivo(atual.direito, valor);
        }

        return atual;
    }

    public boolean buscar(int valor){
        return buscarRecursivo(raiz, valor);
    }

    private boolean buscarRecursivo(Node atual, int valor){
        if (atual == null){
            return false;
        }

        if (valor < atual.valor){
            return buscarRecursivo(atual.esquerdo, valor);
        } else {
            return buscarRecursivo(atual.direito, valor);
        }
    }

    public int calcularAltura() {
        return calcularAlturaRecursivo(raiz);
    }

    private int calcularAlturaRecursivo(Node atual){
        if (atual == null) {
            return 0;
        }

        int alturaEsquerda = calcularAlturaRecursivo(atual.esquerdo);
        int alturaDireita = calcularAlturaRecursivo(atual.direito);

        return Math.max(alturaEsquerda, alturaDireita) + 1;
    }

    public void remover(int valor){
        raiz = removerRecursivo(raiz, valor);
    }

    private Node removerRecursivo(Node atual, int valor){
        if (atual == null) {
            return null;
        }

        if (valor < atual.valor){
            atual.esquerdo = removerRecursivo(atual.esquerdo, valor);
        } else if (valor > atual.valor) {
            atual.direito = removerRecursivo(atual.direito,valor);
        } else {

            if (atual.esquerdo == null){
                return atual.direito;
            } else if (atual.direito == null) {
                return atual.esquerdo;
            }

            atual.valor = encontrarMenorValor(atual.direito);

            atual.direito = removerRecursivo(atual.direito, atual.valor);
        }
        return atual;
    }

    private int encontrarMenorValor(Node raiz){
        int menor = raiz.valor;
        while (raiz.esquerdo != null){
            menor = raiz.esquerdo.valor;
            raiz = raiz.esquerdo;
        }
        return menor;
    }
}
