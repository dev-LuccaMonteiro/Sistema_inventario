import static java.lang.IO.*;

public class Pocao extends Item {
    private final int cura;

    public Pocao(String nome, double peso, String raridade, int cura) {
        super(nome, peso, raridade);
        if (cura <= 0) {
            throw new IllegalArgumentException("A cura da poção deve ser maior que zero.");
        }
        this.cura = cura;
    }

    @Override
    public void equipar() {
        print("Consumindo: ");
        getStatus();
        println("");
    }

    @Override
    public void getStatus() {
        print("[Poção] ");
        super.getStatus();
        println(" | Cura: " + cura + " HP");
    }
}