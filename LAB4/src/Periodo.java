public class Periodo {

    private int ano;

    private int semestre;  // 1 ou 2

    public Periodo(int ano, int semestre) {

        if (semestre < 1 || semestre > 2) {
            throw new RuntimeException("Semestre inválido!!!!");
        }

        this.ano = ano;
        this.semestre = semestre;
    }

    @Override
    public String toString() {
        return "Periodo{" +
                "ano=" + ano +
                ", semestre=" + semestre +
                "}";
    }

    public int getAno() {
        return ano;
    }

    public int getSemestre() {
        return semestre;
    }
}
