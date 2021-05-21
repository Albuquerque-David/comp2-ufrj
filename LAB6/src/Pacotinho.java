import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pacotinho<T extends Colecionavel> {

    private List<T> colecionaveis;
    private T[] colecionaveisArray;
    private String tipo;

    public Pacotinho(Repositorio repo, int[] posicoesDesejadas, String tipo) {
        this.tipo = tipo;
        colecionaveis = new ArrayList<T>();
        for( Integer posicao : posicoesDesejadas ){
            colecionaveis.add((T) repo.getColecionavel(posicao));
        }
    }

    /**
     * Produz um pacotinho com quantFigurinhas sorteadas aleatoriamente
     * dentre aqueles presentes no repositório informado.
     *
     * @param repo o repositório desejado
     * @param quantFigurinhas a quantidade de figurinhas a constar no pacotinho
     */
    public Pacotinho(Repositorio repo, int quantFigurinhas, String tipo) {
        this.tipo = tipo;
        Integer tamanho = repo.getTotalColecionaveis();
        colecionaveis = new ArrayList<T>();
        Random rand = new Random();
        int n;
        for(int i = 0; i < quantFigurinhas; i++ ){
            n = rand.nextInt(tamanho-1) + 1;
            colecionaveis.add((T) repo.getColecionavel(n));
        }
    }

    public T[] getColecionaveis() {
            colecionaveisArray = (T[]) ColecionavelFactory.createArray(tipo,colecionaveis.size());
            colecionaveis.toArray(colecionaveisArray);
            return colecionaveis.toArray(colecionaveisArray);
    }
}
