import java.awt.*;

public class Selo implements Colecionavel{

    public float getValorNominal(){
        return 0f;
    }

    public String getPais(){
        return null;
    }

    @Override
    public Image getImagem() {
        return null;
    }

    @Override
    public int getPosicao() {
        return 0;
    }
}
