package arvore;

import java.util.Scanner;

import static arvore.CaixeiroViajante.calcularRotaVizinhoMaisProximo;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcaoArvore = -1;

        String menuPrincipal = """
                ---------------MENU DE ARVORES-------------
                1. Árvore Binária de Busca (BST)
                2. Árvore AVL
                3. Árvore Rubro-Negro
                4. Resolver o Caixeiro-Viajante (TSP)
                0. Sair do Programa
                """;
        String menuSecundario = """
                \\n--- OPERAÇÕES NA ÁRVORE
                  1. Inserir múltiplos valores de uma vez
                  2. Inserir apenas um valor
                  3. Buscar um valor
                  4. Remover um valor
                  5. Ver Altura da Árvore
                  9. Voltar ao Menu Principal
                  Escolha a operação:
                """;

        while (opcaoArvore != 0){
            System.out.println(menuPrincipal);
            opcaoArvore = scanner.nextInt();

            if (opcaoArvore == 1 || opcaoArvore == 2 || opcaoArvore == 3){
                ArvoreBinariaBusca bst = new ArvoreBinariaBusca();
                ArvoreAVL avl = new ArvoreAVL();
                ArvoreRubroNegro rn = new ArvoreRubroNegro();
                int operacao = -1;

                String nomeArvore = "";
                if (opcaoArvore == 1) nomeArvore = "BST (Sem Balanceamento)";
                else if (opcaoArvore == 2) nomeArvore = "AVL (Foco na Altura)";
                else nomeArvore = "Rubro-Negra (Foco nas Cores)";

                while (operacao != 9) {
                    System.out.println(menuSecundario);
                    operacao = scanner.nextInt();

                    switch (operacao) {
                        case 1:
                            System.out.println("Quantos valores você quer colocar?");
                            int quantidade = scanner.nextInt();
                            System.out.println("Digite os " + quantidade + " valores");
                            for (int i = 0; i < quantidade; i++) {
                                int valor = scanner.nextInt();
                                if (opcaoArvore == 1) bst.inserir(valor);
                                else if (opcaoArvore == 2) avl.inserir(valor);
                                else rn.inserir(valor);
                            }
                            System.out.println("Valores inseridos com sucesso");
                            break;

                        case 2:
                            System.out.println("Digite o valor para inserir: ");
                            int valInserir = scanner.nextInt();
                            if (opcaoArvore == 1) bst.inserir(valInserir);
                            else if (opcaoArvore == 2) avl.inserir(valInserir);
                            else rn.inserir(valInserir);
                            System.out.println("Valor " + valInserir + " inserido!");
                            break;

                        case 3:
                            System.out.println("Digite o valor que deseja buscar: ");
                            int valBusca = scanner.nextInt();
                            boolean encontrou = false;

                            if (opcaoArvore == 1) encontrou = bst.buscar(valBusca);
                            else if (opcaoArvore == 2) encontrou = avl.buscar(valBusca);
                            else encontrou = rn.buscar(valBusca);

                            if (encontrou) System.out.println("O valor "+ valBusca+ "Está na árvore");
                            else System.out.println("O valor " + valBusca + "Não foi encontrado.");
                            break;

                        case 4:
                            if (opcaoArvore == 3) {
                                System.out.println("Não foi feita a implementação da remoção da RN");
                            } else {
                                System.out.println("Informe o valor para remover: ");
                                int valRemover = scanner.nextInt();
                                if (opcaoArvore == 1) bst.remover(valRemover);
                                else avl.remover(valRemover);
                                System.out.println("Comando de remoção executado para o valor " + valRemover);
                            }
                            break;

                        case 5:
                            int altura = 0;
                            if (opcaoArvore == 1) altura = bst.calcularAltura();
                            else if (opcaoArvore == 2) altura = avl.calcularAltura();
                            else altura = rn.calcularAltura();
                            System.out.println("A altura atual da árvore: " + altura );
                            break;

                        case 9:
                            System.out.println("Voltando ao menu principal...");
                            break;

                        default:
                            System.out.println("Opção inválida");
                    }
                }
            } else if (opcaoArvore == 4) {
                System.out.println("Problema do Caixeiro");
                System.out.println("De qual cidade quer começar? (DIGITE 0 a 3)");
                int cidadeInicio = scanner.nextInt();
                int[][] mapa = {
                        {0, 10, 15, 20},
                        {10, 0, 35, 25},
                        {15, 35, 0, 30},
                        {20, 25, 30, 0}
                };
                CaixeiroViajante.calcularRotaVizinhoMaisProximo(mapa, cidadeInicio);
                System.out.println("Iniciando a viagem a partir da Cidade 0");
            } else if (opcaoArvore != 0) {
                System.out.println("Opção inválida");
            }
        }

        System.out.println("Programa encerrado");
        scanner.close();
    }
}
