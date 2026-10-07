import static java.lang.IO.*;

public class Arma extends Item {
    private final int dano;

    public Arma(String nome, double peso, String raridade, int dano) {
        super(nome, peso, raridade);
        if (dano <= 0) {
            throw new IllegalArgumentException("O dano da arma deve ser maior que zero.");
        }
        this.dano = dano;
    }

    @Override
    public void equipar() {
        print("Equipando: ");
        getStatus();
        println("");
    }

    @Override
    public void getStatus() {
        print("[Arma] ");
        super.getStatus();
        println(" | Dano: " + dano);
    }
}