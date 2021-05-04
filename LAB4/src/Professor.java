public class Professor extends Pessoa{

    private Integer anoContratacao;

    public Professor(String nome){
        this.nome = nome;
    }

    public Professor(String nome, Integer anoContratacao){
        this.nome = nome;
        this.anoContratacao = anoContratacao;
    }

    public Integer getAnoContratacao() {
        return anoContratacao;
    }

    public void setAnoContratacao(Integer anoContratacao) {
        this.anoContratacao = anoContratacao;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "nome=" + nome +
                ", anoContratacao=" + anoContratacao +
                "}";
    }
}
