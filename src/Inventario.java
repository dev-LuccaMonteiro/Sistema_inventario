import static java.lang.IO.*;
import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private final double limitePeso;
    private final List<Item> itens;

    public Inventario(double limitePeso) {
        if (limitePeso <= 0) {
            throw new IllegalArgumentException("O limite de peso do inventário deve ser maior que zero.");
        }
        this.limitePeso = limitePeso;
        this.itens = new ArrayList<>();
    }

    public double getPesoAtual() {
        return itens.stream().mapToDouble(item -> item.peso).sum();
    }

    public void adicionar(Item item) {
        if (getPesoAtual() + item.peso > limitePeso) {
            throw new IllegalArgumentException(
                    String.format("Peso limite excedido! (Atual: %.1f kg | Limite: %.1f kg)", getPesoAtual(), limitePeso)
            );
        }
        itens.add(item);
        print("Adicionado com sucesso: ");
        item.getStatus();
    }

    public void equiparItem(int index) {
        validarIndice(index);
        itens.get(index).equipar();
    }

    public void droparItem(int index) {
        validarIndice(index);
        Item itemRemovido = itens.remove(index);
        itemRemovido.dropar();
    }

    public void listarItens() {
        println(String.format("\n=== INVENTÁRIO (%.1f / %.1f kg) ===", getPesoAtual(), limitePeso));
        if (itens.isEmpty()) {
            println("Nenhum item armazenado.");
            return;
        }
        for (int i = 0; i < itens.size(); i++) {
            print((i + 1) + ". ");
            itens.get(i).getStatus();
        }
        println("==================================");
    }

    private void validarIndice(int index) {
        if (index < 0 || index >= itens.size()) {
            throw new IndexOutOfBoundsException("Índice inválido! Não existe item nessa posição.");
        }
    }
}