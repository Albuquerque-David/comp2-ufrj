public class Usuario {

    private String nome;
    private long cpf;
    private String endereco;

    public Usuario(String nome, long cpf, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public String getNome(){
        return nome;
    }

    public long getCpf(){
        return cpf;
    }

    public String getEndereco(){
        return endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return cpf == usuario.cpf;
    }

    @Override
    public int hashCode() {
        return (int) (cpf ^ (cpf >>> 32));
    }
}
