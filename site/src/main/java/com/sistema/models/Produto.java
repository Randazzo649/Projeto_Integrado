package com.sistema.models;

public class Produto {
    private long id;
    private String nome;
    private double preco;
    private String descricao;
    private String tipo;
    private String quantidade;
    private String categoria;
    private String material;
    private String tamanho;
    private String cor;
    private long id_estoque;
    private long id_rel_estoque;

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return this.id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return this.nome;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public double getPreco() {
        return this.preco;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return this.descricao;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getTipo() {
        return this.tipo;
    }
    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
    public String getQuantidade() {
        return this.quantidade;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public String getCategoria() {
        return this.categoria;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
    public String getMaterial() {
        return this.material;
    }
    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }
    public String getTamanho() {
        return this.tamanho;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }
    public String getCor() {
        return this.cor;
    }
    public void setIdEstoque(long id_estoque) {
        this.id_estoque = id_estoque;
    }
    public long getIdEstoque() {
        return this.id_estoque;
    }
    public void setIdRelEstoque(long id_rel_estoque) {
        this.id_rel_estoque = id_rel_estoque;
    }
    public long getIdRelEstoque() {
        return this.id_rel_estoque;
    }
}
