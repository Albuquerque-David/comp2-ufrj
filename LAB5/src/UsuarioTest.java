import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UsuarioTest {

    int tamanhoTeste = 100000;
    List<Usuario> amigos;
    Usuario usuarioIngenuo;
    Usuario usuarioEsperto;
    Usuario usuarioBuscaBinaria;

    @Before
    public void setUp() {

        amigos = new ArrayList<>();

        for( int i = 0; i < tamanhoTeste ; i ++ ){
            amigos.add(new Usuario(i,new CalculadorIntersecaoIngenuo()));
        }

        usuarioIngenuo = new Usuario(tamanhoTeste, new CalculadorIntersecaoIngenuo());
        usuarioIngenuo.getAmigos().addAll(amigos);

        usuarioEsperto = new Usuario(tamanhoTeste+1, new CalculadorIntersecaoEsperto());
        usuarioEsperto.getAmigos().addAll(amigos);

        usuarioBuscaBinaria = new Usuario(tamanhoTeste+2, new CalculadorIntersecaoViaBuscaBinaria());
        usuarioBuscaBinaria.getAmigos().addAll(amigos);

    }

    @Test
    public void testarCalculadorIntersecaoIngenuo(){
        double t1 = System.currentTimeMillis();
        int quantAmigosEmComum = usuarioIngenuo.obterQuantidadeDeAmigosEmComum(usuarioEsperto);
        double t2 = System.currentTimeMillis();
        double tf = (t2-t1)/1000;
        System.out.printf("A execução usando o Calculador Ingenuo demorou %.15f segundos.\n",tf);
    }

    @Test
    public void testarCalculadorIntersecaoEsperto(){
        double t1 = System.currentTimeMillis();
        int quantAmigosEmComum = usuarioEsperto.obterQuantidadeDeAmigosEmComum(usuarioIngenuo);
        double t2 = System.currentTimeMillis();
        double tf = (t2-t1)/1000;
        System.out.printf("A execução usando o Calculador Esperto demorou %.15f segundos.\n",tf);
    }

    @Test
    public void testarCalculadorIntersecaoViaBuscaBinaria(){
        double t1 = System.currentTimeMillis();
        int quantAmigosEmComum = usuarioBuscaBinaria.obterQuantidadeDeAmigosEmComum(usuarioEsperto);
        double t2 = System.currentTimeMillis();
        double tf = (t2-t1)/1000;
        System.out.printf("A execução usando o Calculador Via Busca Binaria demorou %.15f segundos.\n",tf);
    }

    @Test
    public void testarPerformanceCalculadoresIntersecao(){
        double t1 = System.currentTimeMillis();
        int quantAmigosEmComum = usuarioIngenuo.obterQuantidadeDeAmigosEmComum(usuarioEsperto);
        double t2 = System.currentTimeMillis();
        double tf = (t2-t1)/1000;
        System.out.printf("A execução usando o Calculador Ingenuo demorou %.15f segundos.\n",tf);
        t1 = System.currentTimeMillis();
        quantAmigosEmComum = usuarioEsperto.obterQuantidadeDeAmigosEmComum(usuarioIngenuo);
        t2 = System.currentTimeMillis();
        tf = (t2-t1)/1000;
        System.out.printf("A execução usando o Calculador Esperto demorou %.15f segundos.\n",tf);
        t1 = System.currentTimeMillis();
        quantAmigosEmComum = usuarioBuscaBinaria.obterQuantidadeDeAmigosEmComum(usuarioEsperto);
        t2 = System.currentTimeMillis();
        tf = (t2-t1)/1000;
        System.out.printf("A execução usando o Calculador Via Busca Binaria demorou %.15f segundos.\n",tf);
    }

}
