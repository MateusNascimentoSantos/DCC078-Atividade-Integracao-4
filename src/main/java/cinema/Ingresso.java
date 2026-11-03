package cinema;

public abstract class Ingresso {

    private String sessao;
    private String poltrona;
    private String documento;
    private float precoBase;
    private PoliticaPreco politicaPreco;

    public String getSessao() {
        return sessao;
    }

    public void setSessao(String sessao) {
        this.sessao = sessao;
    }

    public String getPoltrona() {
        return poltrona;
    }

    public void setPoltrona(String poltrona) {
        this.poltrona = poltrona;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public float getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(float precoBase) {
        this.precoBase = precoBase;
    }

    public PoliticaPreco getPoliticaPreco() {
        return politicaPreco;
    }

    public void setPoliticaPreco(PoliticaPreco politicaPreco) {
        this.politicaPreco = politicaPreco;
    }

    public abstract String getTipo();

    public abstract void validarDireito();

    public abstract float calcularValor(float precoSessao);

    public String emitir() {
        this.validarDireito();
        CentralReservas.getInstance().reservar(this.sessao, this.poltrona);
        float precoSessao = this.politicaPreco.calcular(this.precoBase);
        return getTipo() + "{" +
                "sessao='" + this.sessao + '\'' +
                ", poltrona='" + this.poltrona + '\'' +
                ", valor=" + this.calcularValor(precoSessao) +
                '}';
    }
}
