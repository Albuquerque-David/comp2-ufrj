import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Turma {

    private Disciplina disciplina;
    private Periodo periodo;
    private Professor professor;
    private Map<Aluno, Float> notasAlunos;

    public Turma(Disciplina disciplina, Periodo periodo, Professor professor){

        this.disciplina = disciplina;
        this.periodo = periodo;
        this.professor = professor;

        notasAlunos = new HashMap<>();

    }

    public void inscreverAluno(Aluno aluno){
        if(aluno == null)
            throw new NullPointerException("Aluno não pode ser nulo.");
        if(Siguinha.obterAluno(aluno.getDre()) == null)
            throw new IllegalArgumentException("Aluno não cadastrado no Siguinha.");
        this.notasAlunos.put(aluno,null);
    }

    public void atribuirMediaFinal(long dre, float nota){
        Aluno aluno = Siguinha.obterAluno(dre);
        aluno.inserirItemHistorico(disciplina,nota,periodo);
        notasAlunos.put(aluno,nota);
    }

    public float obterMediaFinal(long dre){
        Aluno aluno = Siguinha.obterAluno(dre);
        return notasAlunos.get(aluno);
    }

    public String listarAlunos(){
        StringBuilder alunos = new StringBuilder();
        for (Aluno aluno : notasAlunos.keySet()) {
            alunos.append("Aluno: " + aluno.getNome() + "\n");
        }

        return alunos.toString();
    }

    @Override
    public String toString() {
        return "Turma{" +
                "disciplina=" + disciplina +
                ", periodo=" + periodo +
                ", professor=" + professor +
                ", alunos=" + "{"
                + listarAlunos() +
                "}" + "}";
    }
}
