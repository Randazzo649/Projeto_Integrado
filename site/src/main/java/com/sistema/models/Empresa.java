package com.sistema.models;

public class Empresa {
    private long id;
    private String razao;
    private String cnpj;
    private String telefone;
    private String endereco;
    private String email;
    private String senha;
    private String foto;
    private String cor;
    private String nome;

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
    public void setRazao(String razao) {
        this.razao = razao;
    }
    public String getRazao() {
        return razao;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getSenha() {
        return senha;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }
    public String getFoto() {
        return foto;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }
    public String getCor() {
        return cor;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return this.nome;
    }
}
