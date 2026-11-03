package cinema;

public class IngressoFactory {

    public static Ingresso obterIngresso(String ingresso) {
        Class classe = null;
        Object objeto = null;
        try {
            classe = Class.forName("cinema.Ingresso" + ingresso);
            objeto = classe.newInstance();
        } catch (Exception ex) {
            throw new IllegalArgumentException("Ingresso inexistente");
        }
        if (!(objeto instanceof Ingresso)) {
            throw new IllegalArgumentException("Ingresso invalido");
        }
        return (Ingresso) objeto;
    }
}
