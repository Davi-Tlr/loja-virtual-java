package controller;

import dao.PedidoDAO;
import dao.ProdutoDAO;
import model.order.ListaVaziaException;
import model.order.Pedido;
import model.product.Produto;
import model.product.ProdutoDigital;
import model.product.ProdutoFisico;
import service.ProcessadorDePedidos;
import view.MenuView;

import java.util.ArrayList;
import java.util.List;

public class LojaController {
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final MenuView view = new MenuView();
    private final List<Pedido> pedidosEmMemoria = new ArrayList<>();

    public MenuView getView() {
        return view;
    }

    public void cadastrarProduto() {
        int tipo;
        while (true) {
            tipo = view.lerInteiro("Tipo de produto (1 - Físico, 2 - Digital):");
            if (tipo == 1 || tipo == 2) break;
            view.exibirMensagem("Tipo inválido! Deve ser 1 ou 2. Tente novamente.");
        }

        String nome = view.lerString("Nome: ");
        double preco = view.lerDouble("Preço: ");

        Produto produto;
        if (tipo == 1) {
            double peso = view.lerDouble("Peso (kg): ");
            produto = new ProdutoFisico(0, nome, preco, peso);
        } else {
            double tamanhoMB = view.lerDouble("Tamanho do Arquivo (MB): ");
            produto = new ProdutoDigital(0, nome, preco, tamanhoMB);
        }
        produtoDAO.inserir(produto);
        view.exibirMensagem("Produto cadastrado com sucesso!");
    }

    public void listarProdutos() {
        List<Produto> produtos = produtoDAO.listarTodos();
        view.exibirProdutos(produtos);
    }

    public void criarPedido() {
        Pedido pedido = new Pedido();
        view.exibirMensagem("Adicionando produtos ao pedido (digite ID do produto, 0 para parar):");
        listarProdutos();

        while  (true) {
            int id = view.lerInteiro("ID do produto: ");
            if (id == 0) break;
            Produto p = produtoDAO.buscarPorId(id);

            if (p != null) {
                pedido.adicionarProduto(p);
                view.exibirMensagem("Produto adicionado");
            } else {
                view.exibirMensagem("Produto não encontrado! Tente outro ID.");
            }
        }

        if (pedido.getProdutos().isEmpty()) {
            view.exibirMensagem("Pedido vazio, não criado.");
            return;
        }
        try {
            double total = pedido.calcularValorTotal();
            view.exibirMensagem("Pedido criado com total: " + total);
            pedidosEmMemoria.add(pedido);
        } catch (ListaVaziaException e) {
            view.exibirMensagem(e.getMessage());
        }
    }

    public void processarPedido() {
        view.exibirPedidosEmMemoria(pedidosEmMemoria);
        if(pedidosEmMemoria.isEmpty()) return;

        int index;
        while (true) {
            index = view.lerInteiro("Número do pedido a processar (0 para cancelar): ") - 1;
       if (index == -1) return;
       if (index >= 0 && index < pedidosEmMemoria.size()) break;
       view.exibirMensagem("Número inválido! Tente novamente.");
        }

        Pedido pedido = pedidosEmMemoria.get(index);
        Thread thread = new Thread(new ProcessadorDePedidos(pedido));
        thread.start();
        view.exibirMensagem("Processamento iniciado em thread separada.");
    }

    public void salvarPedido() {
        view.exibirPedidosEmMemoria(pedidosEmMemoria);
        if (pedidosEmMemoria.isEmpty()) return;

        int index;
        while (true) {
            index = view.lerInteiro("Número do pedido a salvar (0 para cancelar): ") - 1;
            if (index == -1) return;
            if (index >= 0 && index < pedidosEmMemoria.size()) break;
            view.exibirMensagem("Número inválido! Tente novamente.");
        }

        Pedido pedido = pedidosEmMemoria.get(index);
        pedidoDAO.inserir(pedido);
        pedidosEmMemoria.remove(index);
        view.exibirMensagem("Pedido salvo com sucesso!");
    }

    public void listarPedidosSalvos() {
        List<Pedido> pedidos = pedidoDAO.listarPedidos();
        view.exibirPedidosSalvos(pedidos);
    }

    public void fechar() {
        view.fecharScanner();
    }
}
