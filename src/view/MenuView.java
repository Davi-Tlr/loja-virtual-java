package view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.product.Produto;
import model.order.Pedido;

public class MenuView {
    private final Scanner scanner = new Scanner(System.in);

    public int exibirMenuPrincipal() {
        while (true) {
            System.out.println("\n=== Menu Loja Virtual ===");
            System.out.println("1. Cadastrar produto");
            System.out.println("2. Listar produtos");
            System.out.println("3. Criar pedido");
            System.out.println("4. Processar pedido (com thread)");
            System.out.println("5. Salvar pedido no banco");
            System.out.println("6. Listar pedidos salvos");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            try {
                int opcao = scanner.nextInt();
                scanner.nextLine();
                if (opcao >= 1 && opcao <= 7) {
                    return opcao;
                } else {
                    exibirMensagem("Opção inválida! Escolha entre 1 e 7. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                exibirMensagem("Entrada inválida! Digite um número inteiro. Tente novamente.");
            }
        }
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public int lerInteiro(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int valor = scanner.nextInt();
                scanner.nextLine();
                if (valor < 0) {
                    exibirMensagem("Valor não pode ser negativo! Tente novamente.");
                    continue;
                }
                return valor;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                exibirMensagem("Entrada inválida! Digite um número inteiro. Tente novamente.");
            }
        }
    }

    public double lerDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double valor = scanner.nextDouble();
                scanner.nextLine();
                if (valor < 0) {
                    exibirMensagem("Valor não pode ser negativo! Tente novamente.");
                    continue;
                }
                return valor;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                exibirMensagem("Entrada inválida! Digite um número (use . para decimal). Tente novamente.");
            }
        }
    }

    public String lerString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String valor = scanner.nextLine().trim();
            if (valor.isEmpty()) {
                exibirMensagem("Campo obrigatório! Digite algo. Tente novamente.");
                continue;
            }
            return valor;
        }
    }

    public void exibirProdutos(List<Produto> produtos) {
        if (produtos.isEmpty()) {
            exibirMensagem("Nenhum produto cadastrado.");
            return;
        }
        exibirMensagem("Produtos cadastrados:");
        for (Produto p : produtos) {
            p.exibirInfo();
            System.out.println();
        }
    }

    public void exibirPedidosEmMemoria(List<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            exibirMensagem("Nenhum pedido em memória.");
            return;
        }
        exibirMensagem("Pedidos em memória:");
        for (int i = 0; i < pedidos.size(); i++) {
            System.out.println((i + 1) + ". Pedido com " + pedidos.get(i).getProdutos().size() + " itens");
        }
    }

    public void exibirPedidosSalvos(List<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            exibirMensagem("Nenhum pedido salvo.");
            return;
        }
        exibirMensagem("Pedidos salvos:");
        for (Pedido p : pedidos) {
            System.out.println("ID: " + p.getId() + " | Data: " + p.getData() + " | Total: " + p.getValorTotal());
            p.exibirResumo();
            System.out.println("---");
        }
    }

    public void fecharScanner() {
        scanner.close();
    }
}