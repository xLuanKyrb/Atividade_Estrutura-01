package arvore;

public class Node {
    int valor;
    Node esquerdo;
    Node direito;
    Node pai;
    int altura;
    boolean cor;


    public Node(int valor) {
        this.valor = valor;
        this.esquerdo = null;
        this.direito = null;
        this.pai = null;
        this.altura = 1;
        this.cor = true;
    }

    public Node(int valor, Node esquerdo, Node direito, int altura, boolean cor) {
        this.valor = valor;
        this.esquerdo = esquerdo;
        this.direito = direito;
        this.altura = altura;
        this.cor = cor;
    }
}
