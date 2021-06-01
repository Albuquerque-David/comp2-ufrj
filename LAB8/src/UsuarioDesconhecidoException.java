public class UsuarioDesconhecidoException extends Exception {

    public UsuarioDesconhecidoException(){
        super("Usuário não encontrado. Informe um usuário válido.");
    }

}
