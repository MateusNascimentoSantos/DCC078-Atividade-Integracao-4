package cinema;

public class IngressoInteira extends Ingresso {

    public String getTipo() {
        return "Inteira";
    }

    public void validarDireito() {
    }

    public float calcularValor(float precoSessao) {
        return precoSessao;
    }
}
