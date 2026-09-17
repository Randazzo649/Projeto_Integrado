package com.sistema.models;

public class Foto {
    private long id;
    private String caminho;

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return this.id;
    }
    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }
    public String getCaminho() {
        return this.caminho;
    }
}
