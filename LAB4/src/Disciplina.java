import java.util.Map;

public class Disciplina {

    //DRE Aluno - Nota
    private Map<Integer, Float> notas;

    private String nome;

    private int creditos;

    private String codigo;

    public Disciplina(String nome, int creditos, String codigo) {
        this.nome = nome;
        this.creditos = creditos;
        this.codigo = codigo;
    }

    public Map<Integer, Float> getNotas() {
        return notas;
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "nome=" + nome +
                ", codigo=" + codigo +
                "}";
    }

    public void setNotas(Map<Integer, Float> notas) {
        this.notas = notas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
