public class TamanhoZeroException extends Exception {

    public TamanhoZeroException(){
        super("Tamanho da HashTag não pode ser 0.");
    }

    public TamanhoZeroException(String texto){
        super(texto);
    }
}
