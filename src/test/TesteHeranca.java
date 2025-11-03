package test;
import model.product.*;
import model.order.*;

public class TesteHeranca {
    public static void main(String[] args) {
        Pedido pedido = new Pedido();

        Produto p1 = new ProdutoFisico(01, "Teclado", 200.00, 20 );
        Produto p2 = new ProdutoDigital(02, "E-book Java", 60.00, 15.2);

        pedido.adicionarProduto(p1);
        pedido.adicionarProduto(p2);

        pedido.exibirResumo();
    }
}
