# Sistema de Cadastro e Processamento de Pedidos em uma Loja Virtual

## Descrição
Projeto acadêmico para o 3º semestre, implementando conceitos de POO em Java. 

O sistema simula uma loja virtual com cadastro de produtos (físicos e digitais), 
criação e processamento de pedidos via threads, e persistência em banco de dados 
SQLite.

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

*Projeto desenvolvido para uma atividade de POO em Java*