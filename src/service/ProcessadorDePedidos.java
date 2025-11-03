package service;

import model.order.Pedido;
import model.product.Produto;

public class ProcessadorDePedidos implements Runnable{
    private Pedido pedido;
    public ProcessadorDePedidos(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public void run() {
        String pedidoDesc = (pedido.getId() == 0) ? "em memória" : String.valueOf(pedido.getId());
        System.out.println("Iniciando processamento do pedido " + pedidoDesc + "...");
        int itemCount = 1;
        for (Produto p : pedido.getProdutos()) {
            System.out.println("Processando item " + itemCount + " (" + p.getNome() + ") do pedido " + pedidoDesc + "...");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.out.println("Processamento interrompido para pedido " + pedidoDesc + ": " + e.getMessage());
                Thread.currentThread().interrupt();
            }
            itemCount++;
        }
        System.out.println("Processamento do pedido " + pedidoDesc + " concluído!");
    }
}
