import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.Assert.*;

public class TuiterLiteTest {

    private TuiterLite tuiterLite;
    private Usuario usuario;

    @Before
    public void setUp() throws UsuarioJaExisteException {
        // cria um TuiterLite
        tuiterLite = new TuiterLite();

        // cria um usuário
        usuario = tuiterLite.cadastrarUsuario("Fulano", "fulano@teste.com");

        // cria uma imagem para o usuário
        Image foto = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        usuario.setFoto(foto);
    }

    @Test
    public void testeUsuariosReconhecidosCorretamente() {
        assertEquals("Duas instâncias de Usuario devem ser consideradas iguais se possuirem o mesmo email",
                this.usuario, new Usuario("Fulano de Tal", "fulano@teste.com"));
    }

    @Test
    public void testeAutorDoTuite()
            throws TamanhoMaximoExcedidoException, UsuarioDesconhecidoException, TamanhoZeroException {
        Tuite tuite = tuiterLite.tuitarAlgo(usuario, "Testando");
        assertEquals("O tuíte deve retornar corretamente seu autor",
                usuario, tuite.getAutor());
    }

    @Test
    public void testeTamanhoTuite() throws UsuarioDesconhecidoException, TamanhoMaximoExcedidoException, TamanhoZeroException {
        // testaremos para 100 tamanhos diferentes, todos maiores do que o máximo permitido
        for (int excessoCaracteres = 1; excessoCaracteres <= 100; excessoCaracteres++) {

            // cria uma String muito grande
            int tamanho = TuiterLite.TAMANHO_MAXIMO_TUITES + 1;
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= tamanho; i++) {
                sb.append("x");
            }
            String texto = sb.toString();

            try {
                tuiterLite.tuitarAlgo(usuario, texto);
                fail("Um tuite maior do que o tamanho máximo deve lançar uma TamanhoMaximoExcedidoException");

            } catch (TamanhoMaximoExcedidoException e) {
                assertEquals("A exceção deve comunicar corretamente o tamanho do texto que se tentou tuitar",
                        tamanho, e.getTamanhoTexto());
            }
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testeAnexo()
            throws TamanhoMaximoExcedidoException, UsuarioDesconhecidoException, TamanhoZeroException {

        Tuite tuite = tuiterLite.tuitarAlgo(usuario, "Testando");

        // vamos anexar a foto do usuário no tuíte que ele acabou de fazer
        tuite.anexarAlgo(usuario.getFoto());
        assertEquals("O tuíte deve retornar corretamente o objeto que foi anexado a ele",
                usuario.getFoto(), tuite.getAnexo());

        // agora vamos anexar um outro objeto qualquer ao mesmo tuíte
        Object objeto = new Object();
        tuite.anexarAlgo(objeto);
        assertEquals("O tuíte deve sobrescrever o anexo anterior (se existir) com o novo anexo",
                objeto, tuite.getAnexo());
    }

    @Test
    public void testeApenasUmTipoPermitidoComoAnexo()
            throws TamanhoMaximoExcedidoException, UsuarioDesconhecidoException, UsuarioJaExisteException, TamanhoZeroException {

        // vamos criar um outro TuiterLite aqui, especificando que ele deverá se relacionar com o tipo Image
        TuiterLite<Image> tuiterLiteQueAceitaApenasImagensComoAnexo = new TuiterLite<>();
        tuiterLiteQueAceitaApenasImagensComoAnexo.cadastrarUsuario(usuario.getNome(), usuario.getEmail());
        Tuite<Image> tuite = tuiterLiteQueAceitaApenasImagensComoAnexo.tuitarAlgo(usuario, "Testando");

        // agora vamos anexar
        tuite.anexarAlgo(usuario.getFoto());
        assertNotNull(tuite.getAnexo());

        // Deixe as linhas seguintes comentadas, mas verifique o comportamento desejado indicado abaixo
        // (note que estamos tentando anexar outros tipos de objetos que não são Image).

//        tuite.anexarAlgo(usuario);       // essa linha, se fosse descomentada, daria erro de compilação
//        tuite.anexarAlgo("1234");        // essa linha, se fosse descomentada, daria erro de compilação
//        tuite.anexarAlgo(new Object());  // essa linha, se fosse descomentada, daria erro de compilação
    }

    @Test
    public void testeHashtags()
            throws TamanhoMaximoExcedidoException, UsuarioDesconhecidoException, TamanhoZeroException {

        Tuite tuite = tuiterLite.tuitarAlgo(usuario, "#LAB7 Testando algo com #hashtag ao longo... #teste");

        // vamos testar se as hashtags (palavras iniciadas por #) foram corretamente detectadas

        assertTrue("Hashtags devem ser detectadas automaticamente e adicionadas ao tuíte",
                tuite.getHashtags().contains("#hashtag"));
        assertTrue("Hashtags devem ser detectadas automaticamente inclusive no começo do tuíte",
                tuite.getHashtags().contains("#LAB7"));
        assertTrue("Hashtags devem ser detectadas automaticamente inclusive no fim do tuite",
                tuite.getHashtags().contains("#teste"));

        // e agora vamos ver se não há falsos positivos
        assertFalse(tuite.getHashtags().contains("#algo"));
        assertFalse(tuite.getHashtags().contains("algo"));
        assertFalse(tuite.getHashtags().contains("#paralelepipedo"));

        // finalmente, vamos tuitar outra coisa para ver se as hashtags estão sendo registradas corretamente no sistema
        tuiterLite.tuitarAlgo(usuario, "Repetindo o uso de uma hashtag #LAB7");
        assertEquals("Hashtags devem ser contabilizadas corretamente pelo sistema",
                "#LAB7", tuiterLite.getHashtagMaisComum());
    }

    @Test
    public void testarMultiplosSimbolosDeHashtag() throws UsuarioDesconhecidoException, TamanhoMaximoExcedidoException, TamanhoZeroException {
        Tuite tuite = tuiterLite.tuitarAlgo(usuario, "###LAB7 ######LAB7");
        assertTrue("O número de caracteres # não deve importar",
                tuite.getHashtags().contains("#LAB7"));
        assertFalse("Para consulta, devemos usar sempre uma única #",
                tuite.getHashtags().contains("###LAB7"));
    }


    /////
    /////   ATENÇÃO: Este teste deve rodar rapidamente (poucos segundos)
    /////
    @Test
    public void testePerformanceContabilizacaoDasHashtags()
            throws TamanhoMaximoExcedidoException, UsuarioDesconhecidoException, TamanhoZeroException {

        for (int i = 1; i <= 200_000; i++) {
            String hashtag = String.format("#%d", i);
            tuiterLite.tuitarAlgo(usuario, hashtag);
        }
        tuiterLite.tuitarAlgo(usuario, "#5");
        assertEquals("#5", tuiterLite.getHashtagMaisComum());
    }

    /////
    /////   ATENÇÃO: Este teste deve rodar rapidamente (poucos segundos)
    /////
    @Test
    public void testePerformanceTuites() throws UsuarioJaExisteException, TamanhoMaximoExcedidoException {
        // vamos cadastrar um número grande de usuários
        for (int i = 1; i <= 300_000; i++) {
            String nome = String.format("Usuário %d", i);
            String email = String.format("usuario%d@email.com", i);
            tuiterLite.cadastrarUsuario(nome, email);
        }

        // agora vamos tentar fazer um número grande de tuítes com usuário desconhecido
        Usuario usuarioNaoCadastrado = new Usuario("Usuário Desconhedido", "unknown@void.com");
        for (int i = 1; i <= 300_000; i++) {
            try {
                tuiterLite.tuitarAlgo(usuarioNaoCadastrado, "Teste");
            } catch (UsuarioDesconhecidoException | TamanhoZeroException e) {
                // ok, essa exceção é esperada
            }
        }
    }

    @Test(expected = TamanhoMaximoExcedidoException.class)
    public void testarTamanhoMaximoExcedidoException() throws TamanhoMaximoExcedidoException, UsuarioDesconhecidoException, TamanhoZeroException {
        String texto;
        int tamanho;

        tamanho = TuiterLite.TAMANHO_MAXIMO_TUITES + 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= tamanho; i++) {
            sb.append("x");
        }
        texto = sb.toString();

        tuiterLite.tuitarAlgo(usuario, texto);
    }

    @Test(expected = TamanhoZeroException.class)
    public void testarTamanhoZeroException() throws TamanhoZeroException, UsuarioDesconhecidoException, TamanhoMaximoExcedidoException {
        String texto = "";
        tuiterLite.tuitarAlgo(usuario,texto);
    }

    @Test(expected = UsuarioDesconhecidoException.class)
    public void testarUsuarioDesconhecidoException() throws UsuarioDesconhecidoException, TamanhoMaximoExcedidoException, TamanhoZeroException {
        Usuario usuarioDesconhecido = new Usuario("Fulano Desconhecido", "desconhecido@teste.com");
        String texto = "Sou um Tuite!";
        tuiterLite.tuitarAlgo(usuarioDesconhecido,texto);
    }

    @Test(expected = UsuarioJaExisteException.class)
    public void testarUsuarioJaExistenteException() throws UsuarioJaExisteException{
        Usuario usuarioJaExistente = tuiterLite.cadastrarUsuario("Fulano", "fulano@teste.com");
    }

}