import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Siguinha {

    private static final Siguinha instanciaUnica = new Siguinha();
    private static Map<Long, Aluno> alunos;

    static {
        alunos = new HashMap<>();
    }

    public static Siguinha getInstanciaUnica() {
        return instanciaUnica;
    }

    public final static float MEDIA_MINIMA_PARA_APROVACAO = 5.0f;

    private static Periodo periodoCorrente = null;

    public String instituicaoDeEnsino;

    public static int obterAnoCorrente() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    private static int obterSemestreCorrente() {
        return obterMesCorrente() <= 6 ? 1 : 2;
    }

    public static int obterMesCorrente() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public static Periodo obterPeriodoCorrente() {

        if (periodoCorrente != null) {
            if (periodoCorrente.getAno() != obterAnoCorrente() ||
                    periodoCorrente.getSemestre() != obterSemestreCorrente()) {
                periodoCorrente = null;  // invalida o cache
            }
        }

        if (periodoCorrente == null) {  // verifica o memo ("cache")
            // atualiza o cache
            periodoCorrente = new Periodo(obterAnoCorrente(), obterSemestreCorrente());
        }

        return periodoCorrente;
    }

    public void cadastrarAluno(long dre, String nome){
        Aluno aluno = new Aluno(dre, nome);
        alunos.put(dre,aluno);
    }

    public static Aluno obterAluno(long dre){
        return alunos.get(dre);
    }
}
