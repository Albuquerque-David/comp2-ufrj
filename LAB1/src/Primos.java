public class Primos {

    public static void main(String[] args) {
        obterPrimos(4000);
    }

    /**
     * Retorna um array de inteiros contendo todos os números primos (1,n) utilizando algoritmos auxiliares.
     *
     * @param n limite superior n do conjunto de números a ser procurado os números primos.
     * @return int[] contendo os números primos entre 1 e n.
     */
    public static int[] obterPrimos(int n) {

        /**
         * Com Crivo de Erastótenes.
         */
        double t1 = System.currentTimeMillis();
        int[] primosComCrivo = calculaPrimosComCrivo(40000);
        double t2 = System.currentTimeMillis();
        double tf = (t2-t1)/1000;
        System.out.printf("A execução usando o Crivo de Erastótenes demorou %.15f segundos.\n",tf);

        /**
         * Com método auxiliar.
         */
        t1 = System.currentTimeMillis();
        int[] primos = calculaPrimos(40000);
        t2 = System.currentTimeMillis();
        tf = (t2-t1)/1000;
        System.out.printf("A execução usando um método auxiliar demorou %.15f segundos.\n",tf);

        // printaPrimos(primosComCrivo);
        // printaPrimos(primos);

        return primosComCrivo;
    }

    /**
     * Calcula e retorna um array de inteiros contendo todos os números primos (1,n).
     *
     * @param n limite superior n do conjunto de números a ser procurado os números primos.
     * @return int[] contendo os números primos entre 1 e n.
     */
    public static int[] calculaPrimos(int n) {
        int[] primos = new int[n];
        int k = 0;
        int cont = 0;

        for(int i = 2; i <= n; i++){
            cont = 0;
            for(int j = 1; j <= n; j++){
                if(i % j == 0)
                    cont++;

                if(cont > 2)
                    break;
            }

            if(cont == 2){
                primos[k] = i;
                k++;
            }
        }

        return primos;
    }

    /**
     * Calcula utilizando o Crivo de Erastótenes e retorna um array de inteiros contendo todos os números primos (1,n).
     *
     * @param n limite superior n do conjunto de números a ser procurado os números primos.
     * @return int[] contendo os números primos entre 1 e n.
     */
    public static int[] calculaPrimosComCrivo(int n) {
        boolean[] calcPrimos = new boolean[n+1];
        int[] primos = new int[n+1];
        int j = 0;

        for(int i = 1; i <= n; i++){
            calcPrimos[i] = true;
        }

        int cont = 2;
        while(cont * cont <= n){

            if(calcPrimos[cont] == true){
                for(int i = cont*cont; i < n+1; i+=cont)
                    calcPrimos[i] = false;
            }

            cont += 1;
        }

        calcPrimos[0] = false;
        calcPrimos[1] = false;

        for(int i = 0; i <= n; i++){
            if(calcPrimos[i]){
                primos[j] = i;
                j++;
            }

        }

        return primos;
    }


    /**
     * Exibe na tela os números primos passados como parâmetro para a função.
     *
     * @param primos array de inteiros contendo os números primos.
     */
    public static void printaPrimos(int[] primos) {
        for(int i = 0; i < primos.length; i++){
            if(primos[i] == 0)
                break;
            System.out.println(primos[i]);
        }

    }

}
