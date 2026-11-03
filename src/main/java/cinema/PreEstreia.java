package cinema;

public class PreEstreia implements PoliticaPreco {

    private float acrescimo = 10.0f;

    public float calcular(float precoBase) {
        return precoBase + acrescimo;
    }
}
