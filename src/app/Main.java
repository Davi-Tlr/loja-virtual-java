package app;

import controller.LojaController;
import database.ConexaoBD;

public class Main {
    public static void main(String[] args) {
        ConexaoBD.criarTabelaProduto();
        ConexaoBD.criarTabelasPedido();

        LojaController controller = new LojaController();
        boolean rodando = true;
        while (rodando) {
            int opcao = controller.getView().exibirMenuPrincipal();
            switch (opcao) {
                case 1 -> controller.cadastrarProduto();
                case 2 -> controller.listarProdutos();
                case 3 -> controller.criarPedido();
                case 4 -> controller.processarPedido();
                case 5 -> controller.salvarPedido();
                case 6 -> controller.listarPedidosSalvos();
                case 7 -> rodando = false;
            }
        }
        controller.getView().exibirMensagem("Saindo do sistema. Obrigado!");
        controller.fechar();
    }
}