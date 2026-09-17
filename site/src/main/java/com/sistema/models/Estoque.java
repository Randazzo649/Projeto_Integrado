package com.sistema.models;

public class Estoque {
    private long id;
    private String nome;
    private long id_empresa;
    
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
    public void setIdEmpresa(long id_empresa) {
        this.id_empresa = id_empresa;
    }
    public long getIdEmpresa() {
       return this.id_empresa;
    }
}
