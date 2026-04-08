package arvore;

import java.util.ArrayList;
import java.util.List;

public class Experimento {
    public static void rodarTeste(){
        int tamanhoEntrada = 10000;
        int execucoes = 30;
        List<Long> tempos = new ArrayList<>();

        for (int i = 0; i < execucoes; i++){
            ArvoreAVL avl = new ArvoreAVL();

            long tempoInicio = System.nanoTime();

            for (int j = 0; j < tamanhoEntrada; j++){
                avl.inserir((int) (Math.random() * 100000));
            }

            long tempoFim = System.nanoTime();
            tempos.add(tempoFim - tempoInicio);
        }
    }
}
