import java.util.ArrayList;
import java.util.List;

public class Repositorio {

    private static final String PREFIXO_URL_IMAGENS = "http://www.nossoalbum.com.br/imagens/";

    private List<Figurinha> todasAsFigurinhas;

    public Repositorio(String sufixoUrlImagens, int quantFigurinhas) {
        todasAsFigurinhas = new ArrayList<>(quantFigurinhas);
        for (int i = 1; i <= quantFigurinhas; i++) {
            Figurinha fig = new Figurinha(i, PREFIXO_URL_IMAGENS + sufixoUrlImagens);
            todasAsFigurinhas.add(fig);
        }
    }

    public int getTotalFigurinhas() {
        return this.todasAsFigurinhas.size();
    }

    public Figurinha getFigurinha(int pos){
        if(todasAsFigurinhas.size() + 1 <= pos || pos <= 0)
            throw new ArrayIndexOutOfBoundsException("Informe uma posição valida.");
        return todasAsFigurinhas.get(pos-1);
    }
}
