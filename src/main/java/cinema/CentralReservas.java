package cinema;

import java.util.ArrayList;

public class CentralReservas {

    private CentralReservas() {};
    private static CentralReservas instance = new CentralReservas();
    public static CentralReservas getInstance() {
        return instance;
    }

    private ArrayList reservas = new ArrayList();

    public void reservar(String sessao, String poltrona) {
        if (this.estaReservada(sessao, poltrona)) {
            throw new IllegalArgumentException("Poltrona ja reservada");
        }
        this.reservas.add(sessao + "-" + poltrona);
    }

    public boolean estaReservada(String sessao, String poltrona) {
        return this.reservas.contains(sessao + "-" + poltrona);
    }

    public int getTotalReservas() {
        return this.reservas.size();
    }
}
