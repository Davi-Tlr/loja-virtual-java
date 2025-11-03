package model.order;
import model.product.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private String data;
    private double valorTotal;
    private List<Produto> produtos;

    public Pedido() {
        this.produtos = new ArrayList<>();
        this.data = LocalDate.now().toString();
        this.valorTotal = 0.0;
    }

    public Pedido(int id) {
        this.id = id;
         this.produtos = new ArrayList<>();
        this.data = LocalDate.now().toString();
        this.valorTotal = 0.0;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void adicionarProduto(Produto p) {
        produtos.add(p);
    }

    public void exibirResumo() {
        System.out.println("Resumo do Pedido:");
        for (Produto p : produtos) {
            p.exibirInfo();
        }
    }

    public double calcularValorTotal() throws ListaVaziaException {
        if (produtos.isEmpty()) {
            throw new ListaVaziaException("A lista de produtos está vazia. Não é possível calcular o total.");
        }

        double total = 0.0;
        for (Produto p : produtos) {
            total += p.getPreco();
        }
        this.valorTotal = total;
        return total;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }


}
