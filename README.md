# Sistema de Cadastro e Processamento de Pedidos em uma Loja Virtual

## Descrição
Aplicação console em Java que simula uma loja virtual com cadastro de 
produtos, criação e processamento de pedidos com threads, e persistência 
de dados em SQLite.

## Funcionalidades Principais

- **Cadastrar produto**

  Adiciona produtos **físicos** (com peso se for fisico) ou **digitais** (com tamanho em MB se digital).
Além de Informar nome, preço e atributos específicos.

- **Listar produtos**

  Veja todos os produtos cadastrados, com ID, nome, preço e tipo (físico/digital).

- **Criar pedido**

  Monte um pedido adicionando produtos por ID.

  O sistema calcula automaticamente o valor total e armazena o pedido em memória.

- **Processar pedido (Thread)**

  Simule o processamento dos itens em uma thread separada, com atraso de 1.5s por item para imitar o envio ou embalagem, exibindo o progresso no console.

- **Salvar pedido no banco**

  Persiste os pedidos no banco SQLite, incluindo data, total e itens associados.

- **Listar pedidos salvos**

  Exibe os pedidos armazenados no banco, com ID, data, total e resumo dos itens.

- **Sair**

  Encerra o programa e fecha conexões.

### Conceitos de POO Aplicados

- **Encapsulamento:** Uso de atributos privados e métodos getters/setters;

- **Herança:** Produto → ProdutoFisico / ProdutoDigital;

- **Polimorfismo:** Lista de produtos sendo tratada de forma genérica em Pedido;

- **Exceções personalizadas:** ListaVaziaException;

- **Threads:** ProcessadorDePedidos para simular o processamento simultâneo de pedidos;

- **DAO (Data Access Object):** Padrão para comunicação com o banco de dados.

## Tecnologias Usadas
- Java (JDK 8+)
- JDBC com SQLite
- Conceitos: Herança, Polimorfismo, Exceções, Threads

## Requisitos
- Java JDK instalado (versão 8 ou superior)
- Git para clonar o repo

## Como Rodar
1. Clone o repositório: `git clone https://github.com/seuusuario/LojaVirtualPOOJava.git`
2. Abra no IDE (ex: IntelliJ ou Eclipse).
3. Compile e rode a classe `Main.java`.
4. Siga o menu no console para cadastrar produtos, criar pedidos, etc.

## Estrutura do Projeto
- `model/`: Classes de domínio (Produto, Pedido, etc.)
- `dao/`: Acesso ao banco de dados
- `controller/`: Lógica do app
- `view/`: Interface console

*Projeto desenvolvido como atividade prática da disciplina de POO em Java.*