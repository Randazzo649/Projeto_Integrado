package com.sistema.models;

public class ItemVenda {
    private long id;
    private int quantidade;
    private double preco;
    private double desconto;
    private long id_produto;
    private long id_venda;

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return this.id;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public int getQuantidade() {
        return this.quantidade;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public double getPreco() {
        return this.preco;
    }
    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }
    public double getDesconto() {
        return this.desconto;
    }
    public void setIdProduto(long id_produto) {
        this.id_produto = id_produto;
    }
    public long getIdProduto() {
        return this.id_produto;
    }
    public void setIdVenda(long id_venda) {
        this.id_venda = id_venda;
    }
    public long getIdVenda() {
        return this.id_venda;
    }
}
