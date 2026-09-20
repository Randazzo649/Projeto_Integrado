package com.sistema.models;

public class Modulo{

    private long id;
    private String nome;
    private String descricao;
    private String imagem;

    public String getDescricao() {
        return descricao;
    }
    public long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getImagem() {
        return imagem;
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
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

}