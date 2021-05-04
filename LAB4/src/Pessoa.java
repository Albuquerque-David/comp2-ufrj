public class Pessoa {

    protected String nome;
    protected Integer anoNascimento;
    protected final static int TAMANHO_MAXIMO_DO_NOME = 30;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }
}
