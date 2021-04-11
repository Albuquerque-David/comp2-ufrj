import java.util.Objects;

/**
 * Representa uma fração de forma explícita, guardando numerador e denominador inteiros.
 * Frações equivalentes (matematicamente) devem ser consideradas equals().
 */
public class Fracao {

    private int numerador;
    private int denominador;

    /**
     * Cria uma fração, baseada em seu numerador e denominador.
     * O sinal da fração será inferido a partir do sinal
     * do numerador e do denominador.
     *
     * @param numerador o numerador
     * @param denominador o denominador
     */
    public Fracao(int numerador, int denominador) {

        if (denominador == 0) {
            throw new ArithmeticException("Denominador não pode ser zero!");
        }

        this.numerador = numerador;
        this.denominador = denominador;
    }

    /**
     * Retorna o sinal da fração.
     *
     * @return true, se for positiva ou nula (zero);
     *         false, se for negativa.
     */
    public boolean getSinal() {

        if(this.numerador == 0)
            return true;

        if(this.numerador < 0 && this.denominador < 0)
            return true;
        else if(this.numerador < 0 || this.denominador < 0){
                return false;
            }
            else
                return true;
    }

    /**
     * Retorna o (valor absoluto do) numerador desta fração, ou seja, sempre não-negativo
     * @return o numerador
     */
    public int getNumerador() {
        return Math.abs(this.numerador);
    }

    /**
     * Retorna o (valor absoluto do) denominador desta fração, ou seja, sempre positivo
     * @return o denominador
     */
    public int getDenominador() {
        return Math.abs(this.denominador);
    }

    /**
     * Retorna o valor numérico da fração (com o sinal correto).
     *
     * @return um float, com o valor na melhor precisão possível
     *         Ex.: -1/3 vai retornar 0.33333333
     */
    public float getValorNumerico() {
        float fracao = (float)getNumerador()/getDenominador();
        fracao *= getSinal() ? 1 : -1;
        return fracao;
    }

    /**
     * Retorna uma fração equivalente à esta fração,
     * e que não pode mais ser simplificada (irredutível).
     *
     * @return um outro objeto Fracao, se esta fração for redutível;
     *         esta própria fração (this), se ela já for irredutível
     */
    public Fracao getFracaoGeratriz() {

        int mdc = AritmeticaUtils.mdc(getNumerador(),getDenominador());
        if(mdc == 1)
            return this;
        else
            return new Fracao(numerador/mdc,denominador/mdc);
    }

    @Override
    public String toString() {
        if(getNumerador() == 0)
            return "0";

        String fracao = new String();
        if(!getSinal())
            fracao = fracao.concat("-");
        fracao = fracao.concat(String.valueOf(getNumerador()));

        if(getDenominador() != 1 && getDenominador() != -1){
            fracao = fracao.concat("/");
            fracao = fracao.concat(String.valueOf(getDenominador()));
        }

        return fracao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fracao fracaoParametro = (Fracao) o;
        Fracao fracao = this;

        if(fracao.getSinal() != this.getSinal())
            return false;

        fracaoParametro = fracaoParametro.getFracaoGeratriz();
        fracao = fracao.getFracaoGeratriz();

        return fracao.getNumerador() == fracaoParametro.getNumerador() && fracao.getDenominador() == fracaoParametro.getDenominador();
    }

}
