import static java.lang.IO.*;

public class Armadura extends Item {
    private final int defesa;

    public Armadura(String nome, double peso, String raridade, int defesa) {
        super(nome, peso, raridade);
        if (defesa <= 0) {
            throw new IllegalArgumentException("A defesa da armadura deve ser maior que zero.");
        }
        this.defesa = defesa;
    }

    @Override
    public void equipar() {
        print("Equipando: ");
        getStatus();
        println("");
    }

    @Override
    public void getStatus() {
        print("[Armadura] ");
        super.getStatus();
        println(" | Defesa: " + defesa);
    }
}