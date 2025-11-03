package model.product;

public class Produto {
    private int id;
    private String nome;
    private double preco;

    public Produto() {
        this.id = 0;
        this.nome = "";
        this.preco = 0.0;
    }

    public Produto(int id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public void exibirInfo() {
        System.out.print("ID: " + getId() + " | Nome: " + getNome() + " | Preço: " + getPreco());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

}