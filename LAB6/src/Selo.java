import java.awt.*;

public class Selo implements Colecionavel{

    private final Image imagem;
    private final int posicao;
    private final float valorMonetario;

    public Selo(int posicao, String urlDaImagem, float valorMonetario) {
        this.posicao = posicao;
        this.imagem = obterImagem(urlDaImagem);
        this.valorMonetario = valorMonetario;
    }

    private Image obterImagem(String url) {
        // ToDo baixaria da Internet...
        return null;
    }

    public float getValorNominal(){
        return valorMonetario;
    }

    @Override
    public Image getImagem() {
        return imagem;
    }

    @Override
    public int getPosicao() {
        return posicao;
    }
}
