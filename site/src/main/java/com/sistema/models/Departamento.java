package com.sistema.models;

public class Departamento {
    
    private long id;
    private String nome;
    private String descricao;
    private long idEmpresa;


    public String getDescricao() {
        return descricao;
    }
    public long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public long getIdEmpresa() {
        return idEmpresa;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setIdEmpresa(long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
}
