package com.sistema.models;

public class RelatorioEstoque {
    private long id;
    private String tipo;
    private int quantidade;
    private String data;
    private String observacao;
    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return this.id;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getTipo() {
        return this.tipo;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public int getQuantidade() {
        return this.quantidade;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getData() {
        return this.data;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    public String getObservacao() {
        return this.observacao;
    }
}
