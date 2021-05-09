import java.util.HashSet;
import java.util.List;

public class EncontrarPar {

    public static void encontrarParIngenuo(List<Integer> lista, int k){

        Integer numero1 = null;
        Integer numero2 = null;

        for(Integer i : lista){
            for(Integer j: lista){
                if(i + j == k)
                {
                    numero1 = i;
                    numero2 = j;
                    break;
                }
            }

            if(numero1 != null && numero2 != null)
                break;
        }

        StringBuilder sb = new StringBuilder();
        if(numero1 == null || numero2 == null)
        {
            System.out.println("Não existe 2 números na lista tais que somados são igual a k.");
            return;
        }

        sb.append(numero1);
        sb.append(" + ");
        sb.append(numero2);
        sb.append(" = ");
        sb.append(k);

        System.out.println(sb.toString());
    }

    public static void encontrarParEsperto(List<Integer> lista, int k){
        HashSet<Integer> conjunto = new HashSet<>();

        Integer numero1 = null;
        Integer numero2 = null;

        for (Integer numero : lista) {
            conjunto.add(numero);
        }

        for(Integer i : lista){
            if(conjunto.contains(-i+k)){
                numero1 = i;
                numero2 = -i+k;
                break;
            }

        }

        StringBuilder sb = new StringBuilder();
        if(numero1 == null || numero2 == null)
        {
            System.out.println("Não existe 2 números na lista tais que somados são igual a k.");
            return;
        }

        sb.append(numero1);
        sb.append(" + ");
        sb.append(numero2);
        sb.append(" = ");
        sb.append(k);

        System.out.println(sb.toString());

    }


}
