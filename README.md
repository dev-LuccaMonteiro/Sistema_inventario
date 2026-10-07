# Sistema de Gerenciamento de Inventário RPG

Um sistema em Java orientado a objetos para gestão de inventários de itens em jogos de RPG (Role-Playing Game). O projeto aplica conceitos fundamentais de Programação Orientada a Objetos (POO), validação rigorosa de dados de domínio e manipulação de entrada e saída via terminal.

---

## Sumário

1. [Visão Geral](#visão-geral)
2. [Funcionalidades](#funcionalidades)
3. [Arquitetura e Conceitos de POO](#arquitetura-e-conceitos-de-poo)
4. [Estrutura do Projeto](#estrutura-do-projeto)
5. [Regras de Negócio e Validações](#regras-de-negócio-e-validações)
6. [Pré-requisitos](#pré-requisitos)
7. [Como Executar](#como-executar)
8. [Exemplo de Uso](#exemplo-de-uso)

---

## Visão Geral

O projeto simula a gestão de uma mochila ou inventário com limite de capacidade de peso. O jogador pode criar diferentes tipos de itens (Armas, Armaduras e Poções), adicioná-los ao inventário, listar os itens existentes com seus atributos específicos, equipar/consumir itens e descartá-los (dropar).

A aplicação utiliza recursos modernos de Java (como o recurso de Unnamed Main Class e I/O simplificado disponível a partir do Java 21/22 em preview ou recursos nativos equivalentes).

---

## Funcionalidades

- **Forjamento/Criação de Itens:** Permite cadastrar dinamica e interativamente Armas, Armaduras e Poções com atributos customizados.
- **Controle Rígido de Peso:** O inventário recusa novos itens caso a soma dos pesos exceda a capacidade máxima configurada.
- **Ações Específicas por Tipo de Item:**
  - Armas são equipadas e exibem seu valor de dano.
  - Armaduras são equipadas e exibem seu valor de defesa.
  - Poções são consumidas e exibem a quantidade de cura em HP.
- **Remoção de Itens (Drop):** Permite descartar itens do inventário, liberando espaço em quilos para novos objetos.
- **Validação Robustecida de Entradas:** Tratamento de exceções para entradas incorretas (textos vazios, números negativos, texto em campos numéricos, índices inválidos).

---

## Arquitetura e Conceitos de POO

O projeto foi estruturado seguindo os pilares fundamentais da orientação a objetos:

### 1. Abstração
A classe abstrata `Item` define a estrutura genérica de um objeto do inventário, contendo atributos essenciais como `nome`, `peso` e `raridade`, além de impor a implementação do comportamento de ações.

### 2. Encapsulamento
Os atributos das classes utilizam modificadores de acesso restritos (`protected`, `private`) e muitos são declarados como `final` para garantir imutabilidade dos dados essenciais após a instanciação.

### 3. Herança
As classes `Arma`, `Armadura` e `Pocao` estendem a classe abstrata `Item`, reaproveitando o construtor base e os métodos comuns, enquanto adicionam atributos específicos (`dano`, `defesa` e `cura`).

### 4. Polimorfismo
- **Interface `Acoes`:** Contrato que exige as operações `equipar()` e `dropar()`.
- **Sobrescrita (`@Override`):** Cada tipo de item implementa o método `equipar()` de maneira própria (Arma e Armadura são equipadas, enquanto Poção é consumida). O método `getStatus()` também é sobrescrito para complementar as informações específicas de cada subclasse.

---

## Estrutura do Projeto

O código está dividido nas seguintes unidades:

- `Acoes.java` (Interface)
  Define o contrato de métodos operacionais (`equipar()` e `dropar()`).

- `Item.java` (Classe Abstrata)
  Implementa `Acoes` e serve como superclasse para todos os itens. Contém as validações primárias de construtor (nome não vazio, peso positivo, etc.).

- `Arma.java` (Classe Concreta)
  Subclasse de `Item`. Adiciona o atributo `dano` e ajusta o comportamento de visualização e equipamento.

- `Armadura.java` (Classe Concreta)
  Subclasse de `Item`. Adiciona o atributo `defesa` e define visualização e equipamento específicos.

- `Pocao.java` (Classe Concreta)
  Subclasse de `Item`. Adiciona o atributo `cura` e implementa o consumo do item.

- `Inventario.java` (Classe Gerenciadora)
  Encapsula a coleção `List<Item>` e a regra de peso limite. Responsável por adicionar, listar, equipar e remover itens por índice.

- `Main.java` (Classe Principal)
  Ponto de entrada da aplicação. Gerencia o loop do menu interativo no terminal e realiza o tratamento de erros do usuário.

---

## Regras de Negócio e Validações

O sistema foi desenhado para impedir que instâncias inválidas sejam criadas ou permaneçam na memória:

1. **Validação do Inventário:**
   - O limite de peso inicial deve ser estritamente maior que zero.
   - Não é possível adicionar um item cujo peso individual ultrapasse a capacidade restante do inventário.
2. **Validação de Itens:**
   - `nome` e `raridade` não podem ser nulos ou em branco.
   - `peso` deve ser um valor estritamente positivo (`> 0`).
   - `dano`, `defesa` e `cura` devem ser inteiros estritamente positivos (`> 0`).
3. **Validação de Acesso:**
   - Tentativas de equipar ou remover itens com índices fora dos limites da lista geram exceções do tipo `IndexOutOfBoundsException`, capturadas amigavelmente pela interface.

---

## Pré-requisitos

- **Java Development Kit (JDK):** Versão 21 ou superior (devido ao uso de `Implicitly Declared Classes` e novos métodos de I/O em `java.lang.IO`).
- **Terminal/Prompt de Comando** para compilação e execução.

---

## Como Executar

1. **Clonar ou Baixar o Repositório:**
   Certifique-se de que todos os arquivos `.java` estejam na mesma pasta.

2. **Compilação:**
   Abra o terminal no diretório dos arquivos e execute:
   ```bash
   javac --enable-preview --release 21 *.java
   ```
   *(Nota: Se estiver usando o JDK 21/22 com suporte às APIs de visualização I/O, utilize a flag `--enable-preview`).*

3. **Execução:**
   Inicie a classe principal com o comando:
   ```bash
   java --enable-preview Main
   ```

---

## Exemplo de Uso

Ao iniciar a aplicação, o menu principal é exibido:

```text
-----------------------------
1. Criar e Adicionar Arma
2. Criar e Adicionar Armadura
3. Criar e Adicionar Poção
4. Listar Inventário
5. Equipar Item
6. Dropar Item
0. Sair
-----------------------------
Escolha uma opção: 
```

### Exemplo de criação de item:
```text
--- Forjando Arma ---
insira o nome da Arma: Espada Longa
insira o peso (kg): 4.5
insira a raridade (ex: Épico, Comum): Rara
insira o dano da arma: 35
Adicionado com sucesso: [Arma] Espada Longa [Rara] (4.5 kg) | Dano: 35
```

### Exemplo de listagem:
```text
=== INVENTÁRIO (4.5 / 15.0 kg) ===
1. [Arma] Espada Longa [Rara] (4.5 kg) | Dano: 35
==================================