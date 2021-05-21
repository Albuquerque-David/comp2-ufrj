public class ColecionavelFactory {

    public static Colecionavel create(String tipo, int posicao, String urlDaImagem) {

        switch (tipo.toLowerCase()) {
            case "figurinha":
                return new Figurinha(posicao, urlDaImagem);
            case "selo":
                return new Selo(posicao, urlDaImagem, 0);
            default:
                return null;
        }
    }

    public static Colecionavel[] createArray(String tipo, int size) {

        switch (tipo.toLowerCase()) {
            case "figurinha":
                return new Figurinha[size];
            case "selo":
                return new Selo[size];
            default:
                return null;
        }
    }
}
