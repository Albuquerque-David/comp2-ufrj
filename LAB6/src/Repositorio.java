import java.util.ArrayList;
import java.util.List;

public class Repositorio<T extends Colecionavel> {

    private static final String PREFIXO_URL_IMAGENS = "http://www.nossoalbum.com.br/imagens/";

    private List<T> todosOsColecionaveis;
    private String tipo;

    public Repositorio(String sufixoUrlImagens, int quantFigurinhas, String tipo) {
        todosOsColecionaveis = new ArrayList<>(quantFigurinhas);
        this.tipo = tipo;
        for (int i = 1; i <= quantFigurinhas; i++) {
            T fig = (T) ColecionavelFactory.create(
                    tipo, i, PREFIXO_URL_IMAGENS + sufixoUrlImagens);
            todosOsColecionaveis.add(fig);
        }
    }

    public int getTotalColecionaveis() {
        return this.todosOsColecionaveis.size();
    }

    public Colecionavel getColecionavel(int pos){
        if(todosOsColecionaveis.size() + 1 <= pos || pos <= 0)
            throw new ArrayIndexOutOfBoundsException("Informe uma posição valida.");
        return todosOsColecionaveis.get(pos-1);
    }
}
