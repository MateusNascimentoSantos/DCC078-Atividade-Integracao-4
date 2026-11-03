package cinema;

public class DiaPromocional implements PoliticaPreco {

    private float precoUnico = 12.0f;

    public float calcular(float precoBase) {
        return precoUnico;
    }
}
