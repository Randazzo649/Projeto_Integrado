package com.sistema.models;

public class Empresa {
    private long id;
    private String razao;
    private String cnpj;
    private String telefone;
    private String endereco;
    private String foto;
    private String cor;
    private String nome;
    private boolean ativo;

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return this.id;
    }
    public void setRazao(String razao) {
        this.razao = razao;
    }
    public String getRazao() {
        return this.razao;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public String getCnpj() {
        return this.cnpj;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getTelefone() {
        return this.telefone;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getEndereco() {
        return this.endereco;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }
    public String getFoto() {
        return this.foto;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }
    public String getCor() {
        return this.cor;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return this.nome;
    }
    public void setAtivo(boolean ativo){
        this.ativo = ativo;
    }
    public boolean isAtivo(){
        return this.ativo;
    }
}
