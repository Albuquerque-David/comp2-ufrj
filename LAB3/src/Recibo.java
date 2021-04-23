import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Recibo {

    private float valorTotalDaCompra;
    private Usuario usuario;
    private Produto produto;
    private int quantidadeComprada;
    private static DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
    private static DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();
    private static char sep = symbols.getDecimalSeparator();

    public Recibo(Usuario usuario, Produto produto, int quantidadeComprada){
        this.usuario = usuario;
        this.produto = produto;
        this.quantidadeComprada = quantidadeComprada;
        this.valorTotalDaCompra = produto.getPrecoEmReais() * quantidadeComprada;
    }

    public float getValorTotalDaCompra() {
        return valorTotalDaCompra;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto(){
        return produto;
    }

    public int getQuantidadeComprada(){
        return quantidadeComprada;
    }

    @Override
    public String toString() {
        String toString = "Recibo no valor de R$" + String.format("%.2f",valorTotalDaCompra) + " para "+ usuario.getNome() +
                " referente à compra de " + quantidadeComprada + " unidades de Livro: " +
                produto.getDescricao();
        toString = toString.replace(".", String.valueOf(sep));
        return toString;
    }

}
