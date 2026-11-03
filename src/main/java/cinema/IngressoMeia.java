package cinema;

public class IngressoMeia extends Ingresso {

    public String getTipo() {
        return "Meia";
    }

    public void validarDireito() {
        if (this.getDocumento() == null) {
            throw new IllegalArgumentException("Meia-entrada sem documento");
        }
    }

    public float calcularValor(float precoSessao) {
        return precoSessao / 2;
    }
}
