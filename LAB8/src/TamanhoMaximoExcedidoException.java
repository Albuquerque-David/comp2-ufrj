public class TamanhoMaximoExcedidoException extends Exception {

    private int tamanhoTexto;

    public TamanhoMaximoExcedidoException(){
        super("Tamanho máximo da HashTag excedida.");
    }

    public TamanhoMaximoExcedidoException(int tamanhoTexto){
        super("Tamanho máximo da HashTag de " + tamanhoTexto + " caracteres excedida.");
        this.tamanhoTexto = tamanhoTexto;
    }

    public TamanhoMaximoExcedidoException(String texto){
        super(texto);
    }

    public int getTamanhoTexto() {
        return tamanhoTexto;
    }
}
