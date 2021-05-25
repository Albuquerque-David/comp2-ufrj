import java.util.*;

/**
 *  Esta classe implementa um sistema de mensagens curtas estilo Twitter.
 *  É preciso cadastrar um usuário, identificado pelo seu e-mail, para que tuítes possam ser feitos.
 *  Usuários começam como iniciantes, depois são promovidos a senior e a ninja, em função do número de tuítes.
 *  Existe um tamanho máximo permitido por mensagem (constante TAMANHO_MAXIMO_TUITES).
 *  As mensagens podem conter hashtags (palavras iniciadas por #), que são detectadas automaticamente.
 *  Os tuítes podem conter, além da mensagem de texto, um anexo qualquer.
 *  Há um método para retornar, a qualquer momento, qual a hashtag mais usada em toda a história do sistema.
 */
public class TuiterLite {

    public static int TAMANHO_MAXIMO_TUITES = 120;
    private Map<String, Usuario> usuarios;
    private Map<Usuario,List<Tuite>> tuites;
    private Map<String, Integer> hashtags;

    public TuiterLite(){
        this.usuarios = new HashMap<>();
        this.tuites = new HashMap<>();
        this.hashtags = new HashMap<>();
    }

    /**
     * Cadastra um usuário, retornando o novo objeto Usuario criado.
     * @param nome O nome do usuário.
     * @param email O e-mail do usuário (precisa ser único no sistema).
     * @return O Usuario criado.
     */
    public Usuario cadastrarUsuario(String nome, String email) {
        if(usuarios.containsKey(email))
            // throw UsuarioJaExisteException
            return null;

        usuarios.put(email, new Usuario(nome,email));
        tuites.put(usuarios.get(email), new ArrayList<>());
        return usuarios.get(email);
    }

    /**
     *
     * @param usuario O autor do tuíte
     * @param texto O texto desejado
     * @return Um "tuíte", que será devidamente publicado no sistema
     *
     * PS.: Se o texto exceder o limite pré-definido, ou o usuário não estiver cadastrado, return null
     */
    public Tuite tuitarAlgo(Usuario usuario, String texto) {
        if(texto.length() > TAMANHO_MAXIMO_TUITES)
            // throw TamanhoMaximoExcedidoException
            return null;

        if(!usuarios.containsKey(usuario.getEmail()))
            return null;
        /*
        Detecta as hashtags
         */

        Set<String> hashtagsDetectadas = new HashSet<>();
        for(int i = 0; i < texto.length(); i++){
            if(texto.charAt(i) == '#')
            {

                for(int j = i+1; j < texto.length(); j++)
                {

                    if((!((int)texto.charAt(j) >= 65 && (int)texto.charAt(j) <= 90) // Entre A-Z
                            && !((int)texto.charAt(j) >=  48 && (int)texto.charAt(j) <= 57) // Entre 0-9
                            && !((int)texto.charAt(j) >=  97 && (int)texto.charAt(j) <= 122) ) // Entre a-z
                            || j == texto.length()-1 // Final do tuite
                      )
                    {
                        if(j == texto.length()-1)
                            hashtagsDetectadas.add(texto.substring(i,texto.length()));
                        else
                            hashtagsDetectadas.add(texto.substring(i,j));
                        break;
                    }
                }
                continue;
            }

        }

        Tuite tuite = new Tuite(usuario,texto, hashtagsDetectadas);
        tuites.get(usuario).add(tuite);

        /*
         Preenche o contador de hashtags
         */
        for( String hashtag :  hashtagsDetectadas){
            if(hashtags.containsKey(hashtag))
                hashtags.put(hashtag,hashtags.get(hashtag)+1);
            else
                hashtags.put(hashtag,1);
        }

        return tuite;
    }

    /**
     * Retorna a hashtag mais comum dentre todas as que já apareceram.
     * A cada tuíte criado, hashtags devem ser detectadas automaticamente para que este método possa funcionar.
     * @return A hashtag mais comum, ou null se nunca uma hashtag houver sido tuitada.
     */
    public String getHashtagMaisComum() {

        String hashtagMaisComum = null;
        Integer quantidadeHashtagMaisComum = 0;

        for(Map.Entry<String, Integer> chaveValor : hashtags.entrySet()){
            String hashtag = chaveValor.getKey();
            Integer quantidade = chaveValor.getValue();

            if(quantidade > quantidadeHashtagMaisComum)
            {
                quantidadeHashtagMaisComum = quantidade;
                hashtagMaisComum = hashtag;
            }

        }
        return hashtagMaisComum;
    }
}
