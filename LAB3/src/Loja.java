import java.util.ArrayList;
import java.util.List;

/**
 * Implementa uma loja virtual para produtos de qualquer tipo,
 * desde que tenham descrição, preço e dimensões.
 *
 * Essa classe será um singleton, isto é, permitiremos apenas
 * uma instância desde objeto no sistema.
 */
public class Loja {

    private static final Loja instanciaUnica = new Loja();
    private List usuarios;
    private List produtos;


    static {
        System.out.println("Estou subindo a classe Loja...");
    }

    protected Loja() {
        usuarios  = new ArrayList<Usuario>();
        produtos = new ArrayList<Produto>();
    }

    public static Loja getInstanciaUnica() {
        return instanciaUnica;
    }

    public void limparEstado() {
        usuarios.clear();
        produtos.clear();
    }

    /**
     * Inclui no estoque da loja a quantidade informado do produto.
     *
     * @param produto o produto a ser incluído
     * @param quantidadeAIncluir a quantidade que será acrescentada à quantidade existente.
     */
    public void incluirProduto(Produto produto, int quantidadeAIncluir) {
        if(produtos.contains(produto)){
            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() + quantidadeAIncluir);
        }
        else{
            produto.setQuantidadeEmEstoque(quantidadeAIncluir);
            produtos.add(produto);
        }
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    /**
     * Efetua a venda do produto desejado na quantidade especificada.
     *
     * @param produto o produto
     * @param quantidadeDesejada a quantidade
     *
     * @return um Recibo indicando a venda feita, se o produto existia (em quantidade suficiente)
     *         no estoque da loja; null, caso o usuário ou o produto sejam desconhecidos,
     *         ou não haja quantidade suficiente do produto desejado
     */
    public Recibo efetuarVenda(
            Produto produto, int quantidadeDesejada, Usuario usuario) {
        if(!produtos.contains(produto))
            return null;

        if(!usuarios.contains(usuario))
            return null;

        if(quantidadeDesejada > produto.getQuantidadeEmEstoque())
            return null;

        if(quantidadeDesejada <= 0)
            return null;

        Recibo recibo = new Recibo(usuario,produto,quantidadeDesejada);
        produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - quantidadeDesejada);
        return recibo;
    }

    /**
     * @param produto o produto a ser consultado
     *
     * @return a quantidade em estoque;
     *         0 se não houver nenhuma unidade;
     *         -1 se o produto não é sequer vendido pela loja
     */
    public int informarQuantidadeEmEstoque(Produto produto) {
        if(!produtos.contains(produto))
            return -1;

        int quantidade = produto.getQuantidadeEmEstoque();

        return quantidade;
    }
}
