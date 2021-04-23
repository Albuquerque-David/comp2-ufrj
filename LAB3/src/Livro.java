public class Livro extends Produto {

    private String nome;
    private String editora;
    private int numerodePaginas;
    private String trechoEmDestaque;
    private String autor;
    private int anoDePublicacao;

    public Livro(String nome, String editora) {
        super(nome,"encurtador.com.br/aopI6");
        this.nome = nome;
        this.editora = editora;
    }

    public String getNome(){
        return nome;
    }

    public String getEditora(){
        return editora;
    }

    public int getNumeroDePaginas() {
        return numerodePaginas;
    }

    public String getTrechoEmDestaque() {
        return trechoEmDestaque;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnoDePublicacao() {
        return anoDePublicacao;
    }
}
