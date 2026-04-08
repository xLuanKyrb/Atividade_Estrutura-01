package arvore;

public class CaixeiroViajante {
    public static void calcularRotaVizinhoMaisProximo(int[][] grafo, int cidadeInicial) {
        int numeroDeCidades = grafo.length;
        boolean[] visitadas = new boolean[numeroDeCidades];
        int[] caminho = new int[numeroDeCidades + 1];
        int custoTotal = 0;

        int cidadeAtual = cidadeInicial;
        visitadas[cidadeAtual] = true;
        caminho[0] = cidadeAtual;

        for (int i = 1; i < numeroDeCidades; i++) {
            int proximaCidade = -1;
            int menorDistancia = Integer.MAX_VALUE;

            for (int j = 0; j < numeroDeCidades; j++) {
                if (!visitadas[j] && grafo[cidadeAtual][j] != 0) {
                    if (grafo[cidadeAtual][j] < menorDistancia){
                    menorDistancia = grafo[cidadeAtual][j];
                    proximaCidade = j;
                }
            }
        }
            cidadeAtual = proximaCidade;
            visitadas[cidadeAtual] = true;
            caminho[i] = cidadeAtual;
            custoTotal += menorDistancia;
    }
        custoTotal +=grafo [cidadeAtual][cidadeInicial];
        caminho[numeroDeCidades] = cidadeInicial;

        System.out.println("Resultado do Caixero-Viajante");
        System.out.print("Caminho:");

        for (int i = 0; i < caminho.length; i++) {
            System.out.println(caminho[i] + (i == caminho.length - 1 ? "" : " -> "));
        }
        System.out.println("Custo total (Distância): " + custoTotal);
    }

    public static void main(String[] args) {
        int[][] mapa = {
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
        };

        System.out.println("Iniciando a viagem a partir da Cidade 0");
        calcularRotaVizinhoMaisProximo(mapa, 0);
    }
}
