import java.util.ArrayList;
import java.util.List;

public class CalculadorIntersecaoIngenuo implements CalculadorIntersecao {

    @Override
    public List<Usuario> obterIntersecao(List<Usuario> lista1, List<Usuario> lista2) {

        // para cada elemento da primeira lista, percorra a segunda lista até encontrá-lo (ou não)
        List<Usuario> intersecao = new ArrayList<>();

        for(Usuario usuario : lista1) {
            if(lista2.contains(usuario))
                intersecao.add(usuario);
        }

        return intersecao;
    }
}
