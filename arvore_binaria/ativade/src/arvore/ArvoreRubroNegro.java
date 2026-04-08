package arvore;

public class ArvoreRubroNegro {
    Node raiz;

    private static final boolean VERMELHO = true;
    private static final boolean PRETO = false;

    private void rotacaoEsquerda(Node x) {
        Node y = x.direito;
        x.direito = y.esquerdo;
        if (y.esquerdo != null) y.esquerdo.pai = x;
        y.pai = x.pai;
        if (x.pai == null) this.raiz = y;
        else if (x == x.pai.esquerdo) x.pai.esquerdo = y;
        else x.pai.direito = y;
        y.esquerdo = x;
        x.pai = y;
    }

    private void rotacaoDireita(Node y) {
        Node x = y.esquerdo;
        y.esquerdo = x.direito;
        if (x.direito != null) x.direito.pai = y;
        x.pai = y.pai;
        if (y.pai == null) this.raiz = x;
        else if (y == y.pai.direito) y.pai.direito = x;
        else y.pai.esquerdo = x;
        x.direito = y;
        y.pai = x;
    }

    public void inserir(int valor) {
        Node novoNode = new Node(valor);

        Node paiAtual = null;
        Node atual = this.raiz;

        while (atual != null) {
            paiAtual = atual;
            if (novoNode.valor < atual.valor) {
                atual = atual.esquerdo;
            } else if (novoNode.valor < atual.valor) {
                atual = atual.direito;
            } else {
                return;
            }
        }

        novoNode.pai = paiAtual;
        if (paiAtual == null) {
            this.raiz = novoNode;
        } else if (novoNode.valor < paiAtual.valor) {
            paiAtual.esquerdo = novoNode;
        } else {
            paiAtual.direito = novoNode;
        }

        if (novoNode.pai == null) {
            novoNode.cor = PRETO;
            return;
        }

        if (novoNode.pai.pai == null) return;

        consertarInsercao(novoNode);
    }

    private void consertarInsercao(Node k) {
        Node tio;

        while (k.pai.cor == VERMELHO) {
            if (k.pai == k.pai.pai.direito) {
                tio = k.pai.pai.direito;

                if (tio != null && tio.cor == VERMELHO) {
                    tio.cor = PRETO;
                    k.pai.cor = PRETO;
                    k.pai.pai.cor = VERMELHO;
                    k = k.pai.pai;
                } else {
                    if (k == k.pai.esquerdo) {
                        k = k.pai;
                        rotacaoDireita(k);
                    }
                    k.pai.cor = PRETO;
                    k.pai.pai.cor = VERMELHO;
                    rotacaoEsquerda(k.pai.pai);
                }
            } else {
                tio = k.pai.pai.direito;

                if (tio != null && tio.cor == VERMELHO) {
                    tio.cor = PRETO;
                    k.pai.cor = PRETO;
                    k.pai.pai.cor = VERMELHO;
                    k = k.pai.pai;
                } else {
                    if (k == k.pai.direito) {
                        k = k.pai;
                        rotacaoEsquerda(k);
                    }
                    k.pai.cor = PRETO;
                    k.pai.pai.cor = VERMELHO;
                    rotacaoDireita(k.pai.pai);
                }
            }
            if (k == raiz) break;
        }
        raiz.cor = PRETO;
    }

    public int calcularAltura(){
    return calcularAlturaRecursivo(raiz);
    }

    public int calcularAlturaRecursivo(Node atual){
        if (atual == null) return 0;
        return Math.max(calcularAlturaRecursivo(atual.esquerdo), calcularAlturaRecursivo(atual.direito)) + 1;
    }

    public boolean buscar (int valor){
        Node atual = raiz;
        while (atual != null){
            if (valor == atual.valor) return true;
            if (valor < atual.valor) atual = atual.esquerdo;
            else atual = atual.direito;
        }
        return false;
    }
}
