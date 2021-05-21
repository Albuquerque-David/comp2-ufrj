import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AlbumColecionavelTest {

    private Album albumSelos;
    private Album albumFigurinhas;
    private Repositorio repositorioSelos;
    private Repositorio repositorioFigurinhas;

    private static final int TAMANHO_DO_ALBUM = 200;
    private static final int ITENS_POR_PACOTE = 3;

    private static final String TIPO_FIGURINHA = "figurinha";
    private static final String TIPO_SELO = "selo";

    @Before
    public void setUp() {
        this.repositorioFigurinhas = new Repositorio("album_copa2014", TAMANHO_DO_ALBUM,TIPO_FIGURINHA);
        this.albumFigurinhas = new Album(repositorioFigurinhas, ITENS_POR_PACOTE);

        this.repositorioSelos = new Repositorio("album_copa2014", TAMANHO_DO_ALBUM,TIPO_SELO);
        this.albumSelos = new Album(repositorioSelos, ITENS_POR_PACOTE);
    }

    private void popularAlbums(int[] posicoesDesejadas) {
        Pacotinho pacoteFigurinhas = new Pacotinho(this.repositorioFigurinhas, posicoesDesejadas, TIPO_FIGURINHA);
        this.albumFigurinhas.receberNovoPacotinho(pacoteFigurinhas);

        Pacotinho pacoteSelos = new Pacotinho(this.repositorioSelos, posicoesDesejadas, TIPO_SELO);
        this.albumSelos.receberNovoPacotinho(pacoteSelos);
    }

    @Test
    public void testarPossuiColecionavelParaColecionaveisExistentes() {
        popularAlbums(new int[] {1, 2, 3});

        for (int i = 1; i <= 3; i++) {
            assertTrue("Figurinhas já inseridas devem ser encontradas",
                    this.albumFigurinhas.possuiItemColado(i));
        }

        for (int i = 1; i <= 3; i++) {
            assertTrue("Selos já inseridas devem ser encontradas",
                    this.albumSelos.possuiItemColado(i));
        }
    }

    @Test
    public void testarPossuiColecionavelParaColecionaveisAusentes() {
        popularAlbums(new int[] {1, 2, 3});

        assertFalse("Não devemos encontrar no álbum figurinhas nunca inseridas",
                this.albumFigurinhas.possuiItemColado(4));
        assertFalse("Não devemos encontrar figurinhas de posições não-positivas",
                this.albumFigurinhas.possuiItemColado(-390));
        assertFalse("Não devemos encontrar figurinhas maiores do que o tamanho",
                this.albumFigurinhas.possuiItemColado(TAMANHO_DO_ALBUM + 1));

        assertFalse("Não devemos encontrar no álbum selos nunca inseridas",
                this.albumSelos.possuiItemColado(4));
        assertFalse("Não devemos encontrar selos de posições não-positivas",
                this.albumSelos.possuiItemColado(-390));
        assertFalse("Não devemos encontrar selos maiores do que o tamanho",
                this.albumSelos.possuiItemColado(TAMANHO_DO_ALBUM + 1));
    }

    @Test
    public void testarPossuiRepetidaParaColecionavelRepetido() {
        popularAlbums(new int[] {1, 2, 3});
        assertFalse(this.albumFigurinhas.possuiItemRepetido(2));
        assertFalse(this.albumSelos.possuiItemRepetido(2));

        popularAlbums(new int[] {2, 8, 9});
        assertTrue(this.albumFigurinhas.possuiItemRepetido(2));
        assertTrue(this.albumSelos.possuiItemRepetido(2));
    }

    @Test
    public void testarGetTamanhoAlbum() {
        assertEquals(TAMANHO_DO_ALBUM, this.albumFigurinhas.getTamanho());
        assertEquals(TAMANHO_DO_ALBUM, this.albumSelos.getTamanho());
    }

    @Test
    public void testarGetContColecionaveis() {
        popularAlbums(new int[] {1, 2, 3});
        assertEquals(3, this.albumFigurinhas.getQuantItensColados());
        assertEquals(3, this.albumSelos.getQuantItensColados());
        popularAlbums(new int[] {1, 2, 3});
        assertEquals(3, this.albumFigurinhas.getQuantItensColados());
        assertEquals(3, this.albumSelos.getQuantItensColados());
    }

    @Test
    public void testarGetQuantasFaltam() {
        popularAlbums(new int[] {1, 2, 3});
        assertEquals(TAMANHO_DO_ALBUM - 3,
                this.albumFigurinhas.getQuantItensFaltantes());
        assertEquals(TAMANHO_DO_ALBUM - 3,
                this.albumSelos.getQuantItensFaltantes());
    }
}