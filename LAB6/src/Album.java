import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Album<T extends Colecionavel> {

    public static final int PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR = 90;

    public static final Image IMAGEM_PADRAO_PARA_POSICAO_VAZIA = null;

    private final Repositorio repositorio;
    private final int quantItensPorPacotinho;

    private List<T> figurinhasColadas;  // direct addressing
    private int quantFigurinhasColadas;

    // poderíamos fazer novamente direct addressing para as repetidas,
    // mas vamos usar um HashMap aqui só para treinarmos
    private Map<Integer, Integer> contRepetidasByPosicao;

    public Album(Repositorio repositorio, int quantItensPorPacotinho) {
        this.repositorio = repositorio;
        this.quantItensPorPacotinho = quantItensPorPacotinho;

        int tamanhoFisicoDaLista = getTamanho() + 1;
        this.figurinhasColadas = new ArrayList<>(tamanhoFisicoDaLista);
        // inicializa as posições com nulls, para poder acessá-las diretamente
        for (int i = 0; i < tamanhoFisicoDaLista; i++) {
            this.figurinhasColadas.add(null);
        }
        this.quantFigurinhasColadas = 0;

        this.contRepetidasByPosicao = new HashMap<>();
    }

    public void receberNovoPacotinho(Pacotinho pacotinho) {
        T[] figurinhasDoPacotinho = (T[]) pacotinho.getColecionaveis();


        if (figurinhasDoPacotinho.length != this.quantItensPorPacotinho) {
            return;
        }

        for (T fig : figurinhasDoPacotinho) {
            final int posicao = fig.getPosicao();
            if (possuiItemColado(posicao)) {
                int contRepetidas = this.contRepetidasByPosicao.getOrDefault(posicao, 0);
                this.contRepetidasByPosicao.put(posicao, contRepetidas + 1);

            } else {
                this.figurinhasColadas.set(posicao, fig);
                this.quantFigurinhasColadas++;
            }
        }



    }

    public T getItemColado(int posicao) {
        if(posicao >= figurinhasColadas.size() || posicao < 0)
            return null;
        return figurinhasColadas.get(posicao);
    }

    public boolean possuiItemColado(int posicao) {
        return getItemColado(posicao) != null;
    }

    public boolean possuiItemRepetido(int posicao) {
        return this.contRepetidasByPosicao.get(posicao) != null;
    }

    public int getTamanho() {
        return this.repositorio.getTotalColecionaveis();
    }

    public int getQuantItensColados() {
        return this.quantFigurinhasColadas;
    }

    public int getQuantItensFaltantes() {
        return getTamanho() - getQuantItensColados();
    }

    public void autoCompletar() {

        if(quantFigurinhasColadas == 0)
            return;

        for (int i = 1; i < figurinhasColadas.size(); i++) {
            if(figurinhasColadas.get(i) == null)
            {
                figurinhasColadas.set(i, (T) repositorio.getColecionavel(i));
                quantFigurinhasColadas++;
            }
        }
    }

    private Image getImagem(int posicao) {
        return possuiItemColado(posicao)
                ? this.getItemColado(posicao).getImagem()
                : IMAGEM_PADRAO_PARA_POSICAO_VAZIA;
    }
}
