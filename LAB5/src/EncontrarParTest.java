import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EncontrarParTest {
    int tamanhoTeste;
    List<Integer> numeros;
    int k;

    @Before
    public void setUp() {
        tamanhoTeste = 20000000;
        k = 123631;
        numeros = new ArrayList<>();

        for(int i = 1; i < tamanhoTeste; i++){
            numeros.add(i);
        }

        Collections.shuffle(numeros);
    }

    @Test
    public void testarEncontrarParIngenuo(){
        EncontrarPar.encontrarParIngenuo(numeros,k);
    }

    @Test
    public void testarEncontrarParEsperto(){
        EncontrarPar.encontrarParEsperto(numeros,k);
    }

    @Test
    public void testarEncontrarParPerformances(){
        double t1 = System.currentTimeMillis();
        EncontrarPar.encontrarParIngenuo(numeros,k);
        double t2 = System.currentTimeMillis();
        double tf = (t2-t1)/1000;
        System.out.printf("A execução usando o Ingenuo demorou %.15f segundos.\n",tf);

        t1 = System.currentTimeMillis();
        EncontrarPar.encontrarParEsperto(numeros,k);
        t2 = System.currentTimeMillis();
        tf = (t2-t1)/1000;
        System.out.printf("A execução usando o Esperto demorou %.15f segundos.\n",tf);
    }

}
