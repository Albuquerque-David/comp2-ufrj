public class Produto {

    private int quantidadeEmEstoque = -1;
    private String descricao;
    private String urlDaImagem;
    private int pesoEmGrama;
    private Dimensoes dimensoes;
    private float precoEmReais;

    public Produto(String descricao, String urlDaImagem) {
        this.descricao = descricao;
        this.urlDaImagem = urlDaImagem;
    }

    /**
     * @return uma descrição textual do produto
     */
    public String getDescricao() {
        return descricao;
    }

    public int getPesoEmGramas() {
        return pesoEmGrama;
    }

    public Dimensoes getDimensoes() {
        return dimensoes;
    }

    public float getPrecoEmReais() {
        return precoEmReais;
    }

    public void setPrecoEmReais(float preco) {
        this.precoEmReais = preco;
    }

    public String getUrlDaImagem() {
        return urlDaImagem;
    }

    public int getQuantidadeEmEstoque(){
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque){
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }
}
