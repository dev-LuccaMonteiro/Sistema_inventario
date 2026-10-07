import static java.lang.IO.*;

public abstract class Item implements Acoes {
    protected final String nome;
    protected final double peso;
    protected final String raridade;

    public Item(String nome, double peso, String raridade) {
        // Validações de Domínio (Garante que o item nasce válido)
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do item não pode ser vazio.");
        }
        if (peso <= 0) {
            throw new IllegalArgumentException("O peso deve ser maior que zero.");
        }
        if (raridade == null || raridade.isBlank()) {
            throw new IllegalArgumentException("A raridade não pode ser vazia.");
        }

        this.nome = nome.trim();
        this.peso = peso;
        this.raridade = raridade.trim();
    }

    public void getStatus() {
        print(nome + " [" + raridade + "] (" + peso + " kg)");
    }

    @Override
    public void dropar() {
        print(" Item dropado: ");
        getStatus();
        println("");
    }
}