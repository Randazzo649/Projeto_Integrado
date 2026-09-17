package com.sistema.models;

public class Venda{
    private long id;
    private String data_venda;
    private String observacao;
    private long id_funconario;
    private long id_cliente;

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return this.id;
    }
    public void setData_venda(String data_venda) {
        this.data_venda = data_venda;
    }
    public String getData_venda() {
        return this.data_venda;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    public String getObservacao() {
        return this.observacao;
    }
    public void setIdFunconario(long id_funconario) {
        this.id_funconario = id_funconario;
    }
    public long getIdFunconario() {
        return this.id_funconario;
    }
    public void setIdCliente(long id_cliente) {
        this.id_cliente = id_cliente;
    }
    public long getIdCliente() {
        return this.id_cliente;
    }
}