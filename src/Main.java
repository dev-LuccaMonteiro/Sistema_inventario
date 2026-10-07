import static java.lang.IO.*;

    void main() {
        Inventario inventario = new Inventario(15.0); // Limite de 15 kg
        boolean ex= true;

        while (ex) {
            println("\n-----------------------------");
            println("1. Criar e Adicionar Arma");
            println("2. Criar e Adicionar Armadura");
            println("3. Criar e Adicionar Poção");
            println("4. Listar Inventário");
            println("5. Equipar Item");
            println("6. Dropar Item");
            println("0. Sair");
            println("-----------------------------");

            String entradaOpcao = readln("Escolha uma opção: ");
            if (entradaOpcao == null || entradaOpcao.isBlank()) continue;

            try {
                int opcao = Integer.parseInt(entradaOpcao.trim());

                switch (opcao) {
                    case 1 -> {
                        println("\n--- Forjando Arma ---");
                        String nome = lerTextoValido("insira o nome da Arma: ");
                        double peso = lerDoubleValido("insira o peso (kg): ");
                        String raridade = lerTextoValido("insira a raridade (ex: Épico, Comum): ");
                        int dano = lerIntValido("insira o dano da arma: ");

                        inventario.adicionar(new Arma(nome, peso, raridade, dano));
                    }
                    case 2 -> {
                        println("\n--- Forjando Armadura ---");
                        String nome = lerTextoValido("insira o nome da Armadura: ");
                        double peso = lerDoubleValido("insira o peso (kg): ");
                        String raridade = lerTextoValido("insira a raridade: ");
                        int defesa = lerIntValido("insira a defesa da armadura: ");

                        inventario.adicionar(new Armadura(nome, peso, raridade, defesa));
                    }
                    case 3 -> {
                        println("\n--- Preparando Poção ---");
                        String nome = lerTextoValido("insira o nome da Poção: ");
                        double peso = lerDoubleValido("insira o peso (kg): ");
                        String raridade = lerTextoValido("insira a raridade: ");
                        int cura = lerIntValido("insira os pontos de cura: ");

                        inventario.adicionar(new Pocao(nome, peso, raridade, cura));
                    }
                    case 4 -> inventario.listarItens();
                    case 5 -> {
                        inventario.listarItens();
                        int pos = lerIntValido("insira o número do item para equipar: ") - 1;
                        inventario.equiparItem(pos);
                    }
                    case 6 -> {
                        inventario.listarItens();
                        int pos = lerIntValido("insira o número do item para dropar: ") - 1;
                        inventario.droparItem(pos);
                    }
                    case 0 -> {
                        println("Saindo do jogo... Até a próxima!");
                        ex= false;
                    }
                    default -> println(" Opção inválida! Tente novamente.");
                }

            } catch (NumberFormatException e) {
                println("Entrada inválida! Digite apenas números no menu.");
            } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                // Captura os erros gerados pelas regras de negócio (Construtores e Inventário)
                println("\n[ERRO DE VALIDAÇÃO]: " + e.getMessage());
            }
        }
    }

    // --- Métodos Auxiliares para Validação de Entrada de Usuário (UX/Main) ---

    private String lerTextoValido(String mensagem) {
        while (true) {
            String entrada = readln(mensagem);
            if (entrada != null && !entrada.isBlank()) {
                return entrada.trim();
            }
            println(" -> Entrada inválida! O campo não pode ficar em branco.");
        }
    }

    private double lerDoubleValido(String mensagem) {
        while (true) {
            try {
                String entrada = readln(mensagem);
                double valor = Double.parseDouble(entrada.replace(",", "."));

                if (valor <= 0) {
                    println(" -> Entrada inválida! O valor deve ser maior que zero.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                println(" -> Formato inválido! Digite apenas números válidos (Ex: 2.5).");
            }
        }
    }

    private int lerIntValido(String mensagem) {
        while (true) {
            try {
                String entrada = readln(mensagem);
                int valor = Integer.parseInt(entrada.trim());

                if (valor <= 0) {
                    println(" -> Entrada inválida! O valor deve ser maior que zero.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                println(" -> Formato inválido! Digite apenas números inteiros.");
            }
        }
    }
