import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TurmaTest {

    private Siguinha siguinha;
    private Aluno aluno1;
    private Aluno aluno2;
    private Aluno aluno3;
    private Professor prof1;
    private Professor prof2;
    private Disciplina algGraf;
    private Disciplina calculo1;
    private Periodo periodo20201;
    private Periodo periodo20202;
    private Turma turmaCalculo1;
    private Turma turmaAlgGraf;

    @Before
    public void setUp() {
        siguinha = Siguinha.getInstanciaUnica();
        aluno1 = new Aluno(1235, "Aluno1");
        aluno2 = new Aluno(1236, "Aluno2");
        aluno3 = new Aluno(1237, "Aluno3");
        prof1 = new Professor("Professor1",2013);
        prof2 = new Professor("Professor2",2018);
        algGraf = new Disciplina("Algoritmos e Grafos", 4, "MAB704");
        calculo1 = new Disciplina("Calculo 1", 4, "MAB704");
        periodo20201 = new Periodo(2020, 1);
        periodo20202 = new Periodo(2020, 2);
        turmaCalculo1 = new Turma(calculo1,periodo20201,prof1);
        turmaAlgGraf = new Turma(algGraf,periodo20202,prof2);

        siguinha.cadastrarAluno(aluno1.getDre(),aluno1);
        siguinha.cadastrarAluno(aluno2.getDre(),aluno2);
        siguinha.cadastrarAluno(aluno3.getDre(),aluno3);
    }

    @Test
    public void testarInscreverAlunoSiga(){
        siguinha.cadastrarAluno(aluno1.getDre(),aluno1);
        siguinha.cadastrarAluno(aluno2.getDre(),aluno2);
        siguinha.cadastrarAluno(aluno3.getDre(),aluno3);
    }

    @Test
    public void testarObterAlunoSiga(){
        System.out.println(Siguinha.obterAluno(1235).getDre());
        assertEquals(Siguinha.obterAluno(1235).getDre(),aluno1.getDre());
    }

    @Test
    public void testarInscreverAlunoEmTurma(){
        turmaCalculo1.inscreverAluno(aluno1);
    }

    @Test(expected = NullPointerException.class)
    public void testarInscreverAlunoInvalidoEmTurma(){
        Aluno aluno4 = null;
        turmaCalculo1.inscreverAluno(aluno4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testarInscreverAlunoNuloEmTurma(){
        Aluno aluno4 = new Aluno(1222, "Nao cadastrado no Siguinha");
        turmaCalculo1.inscreverAluno(aluno4);
    }

    @Test
    public void testarInscreverMultiplosAlunos(){
        turmaCalculo1.inscreverAluno(aluno1);
        turmaCalculo1.inscreverAluno(aluno2);
        turmaAlgGraf.inscreverAluno(aluno2);
        turmaAlgGraf.inscreverAluno(aluno3);
    }

    @Test
    public void testarAcharAluno(){
        assertEquals(Siguinha.obterAluno(1235).toString(),"Aluno{dre=1235, nome=Aluno1}");
    }

    @Test
    public void testarListarTodosAlunosTurma(){
        turmaAlgGraf.listarAlunos();
    }

    @Test
    public void testarAtribuirMediaAluno(){
        turmaAlgGraf.atribuirMediaFinal(1235, 9.5F);
    }

    @Test
    public void testarObterMediaAluno(){
        turmaAlgGraf.atribuirMediaFinal(1235, 9.5F);
        assertTrue(turmaAlgGraf.obterMediaFinal(1235) == 9.5F);
    }

    @Test
    public void testarListarInfoTurma(){
        turmaAlgGraf.inscreverAluno(aluno2);
        turmaAlgGraf.inscreverAluno(aluno3);

        turmaAlgGraf.toString();
    }

    @Test
    public void testarListarAlunosDeTurma(){
        turmaAlgGraf.inscreverAluno(aluno2);
        turmaAlgGraf.inscreverAluno(aluno3);

        turmaAlgGraf.listarAlunos();
    }

}
