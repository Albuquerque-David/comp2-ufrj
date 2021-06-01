public class UsuarioJaExisteException extends Exception {

    public UsuarioJaExisteException(){
        super("Este usuário já está cadastrado no TuiterLite.");
    }

    public UsuarioJaExisteException(String usuario){
        super("O usuário " + usuario + " já está cadastrado no TuiterLite.");
    }

}
