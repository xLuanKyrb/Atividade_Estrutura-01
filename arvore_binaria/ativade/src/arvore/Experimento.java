package arvore;

import java.util.ArrayList;
import java.util.List;

public class Experimento {
    public static void rodarTeste(){
        int[] tamanhoEntrada = {100, 1000, 10000};
        int execucoes = 30;

        System.out.println("Experimento");

        for (int tamanho : tamanhoEntrada){
            System.out.println("Testando inserção de " + tamanho + " valores aleatórios");

            List<Long> temposBST = new ArrayList<>();
            List<Long> temposAVL = new ArrayList<>();

            for (int i = 0; i < execucoes; i++) {
                ArvoreBinariaBusca bst = new ArvoreBinariaBusca();
                ArvoreAVL avl = new ArvoreAVL();

                int[] valoresAleatorios = new int[tamanho];
                for (int j = 0; j < tamanho; j++) {
                    valoresAleatorios[j] = (int) (Math.random() * 100000);
                }

                long inicioBST = System.nanoTime();
                for (int valor : valoresAleatorios) bst.inserir(valor);
                long fimBST = System.nanoTime();
                temposBST.add(fimBST - inicioBST);

                long inicioAVL = System.nanoTime();
                for (int valor : valoresAleatorios) avl.inserir(valor);
                long fimAVL = System.nanoTime();
                temposAVL.add(fimAVL - inicioAVL);
            }

            System.out.println("Ávore Binária de Busca (BST): ");
            calcularExibirEstatisticas(temposBST);

            System.out.println("Ávore Binária de Busca (AVL): ");
            calcularExibirEstatisticas(temposAVL);
            System.out.println();
        }

        }
        private static void calcularExibirEstatisticas(List<Long> temposNano){
            double soma = 0;
            for (long t : temposNano){
                soma += t;
            }

            double mediaNano = soma / temposNano.size();

            double somaDiferencasQuadrado = 0;
            for (long t : temposNano) {
                somaDiferencasQuadrado += Math.pow(t - mediaNano, 2);
            }
            double variancia = somaDiferencasQuadrado / temposNano.size();
            double desvioPadraoNano = Math.sqrt(variancia);

            double mediaMs = mediaNano / 1_000_000.0;
            double desvioPadraoMs = desvioPadraoNano / 1_000_000.0;

            System.out.printf(" Tempo Médio: %.4f ms\n", mediaMs);
            System.out.printf(" Desvio Padrão: %.4f ms\n", desvioPadraoMs);
    }

    public static void main(String[] args) {
        rodarTeste();
    }
}
