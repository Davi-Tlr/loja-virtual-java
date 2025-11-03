package test;

import model.order.ListaVaziaException;
import model.order.Pedido;
import model.product.ProdutoDigital;
import model.product.ProdutoFisico;

public class TestePedido {
    public static void main(String[] arg) {
        Pedido pedido = new Pedido();

        try {
            double total = pedido.calcularValorTotal();
            System.out.println("Valor Total: R$ " + total);

        } catch (ListaVaziaException e) {
            System.out.println("Erro: " +e.getMessage());
        }

        pedido.adicionarProduto(new ProdutoFisico(1, "Teclado", 200.00, 20.0));
        pedido.adicionarProduto(new ProdutoDigital(2, "E-book Java", 60.0, 15.2));

        try {
            double total = pedido.calcularValorTotal();
            System.out.println("Valor total: R$ " + total);
        } catch (ListaVaziaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
