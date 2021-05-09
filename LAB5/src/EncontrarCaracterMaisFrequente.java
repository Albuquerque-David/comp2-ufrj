import java.util.HashMap;
import java.util.Map;

public class EncontrarCaracterMaisFrequente {

    public static void encontrarCaracterMaisFrequenteIngenuo(String string){

        char maisFrequente = string.charAt(0);
        int frequencia = 0;
        int maiorFrequencia = -1;

        for(int i = 0; i < string.length(); i++){
            frequencia = 0;
            for(int j = 0; j < string.length(); j++){
                if(string.charAt(j) == string.charAt(i))
                    frequencia++;
            }

            if(frequencia > maiorFrequencia){
                maiorFrequencia = frequencia;
                maisFrequente = string.charAt(i);
            }

        }

        StringBuilder sb = new StringBuilder();
        sb.append("O caractere mais frequente é: ");
        sb.append(maisFrequente);

        System.out.println(sb.toString());
    }

    public static void encontrarCaracterMaisFrequenteEsperto(String string){

        Map<Character,Integer> caracteres = new HashMap<>();
        char maisFrequente = string.charAt(0);
        int frequencia = 0;
        int maiorFrequencia = -1;

        for(int i = 0; i < string.length(); i++){
            if(!caracteres.containsKey(string.charAt(i))){
                frequencia = 0;
                for(int j = 0; j < string.length(); j++){
                    if(string.charAt(j) == string.charAt(i))
                        frequencia++;
                }
                caracteres.put(string.charAt(i),frequencia);
            }
        }

        for (Map.Entry<Character, Integer> chaveValor : caracteres.entrySet()) {
            Character caractere = chaveValor.getKey();
            Integer frequenciaCaractere = chaveValor.getValue();

            if(frequenciaCaractere > maiorFrequencia){
                maiorFrequencia = frequenciaCaractere;
                maisFrequente = caractere;
            }

        }

        StringBuilder sb = new StringBuilder();
        sb.append("O caractere mais frequente é: ");
        sb.append(maisFrequente);

        System.out.println(sb.toString());
    }



}

