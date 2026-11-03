package cinema;

public class IngressoCortesia extends Ingresso {

    public String getTipo() {
        return "Cortesia";
    }

    public void validarDireito() {
        if (this.getDocumento() == null) {
            throw new IllegalArgumentException("Cortesia sem voucher");
        }
    }

    public float calcularValor(float precoSessao) {
        return 0;
    }
}
