package com.sistema.models;

public class Cliente {
    private long id;
    private String nome;
    private String email;
    private String telefone;
    private String dataCadastro;
    private String cep;
    private int numero;

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
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getTelefone() {
        return this.telefone;
    }
    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    public String getDataCadastro() {
        return this.dataCadastro;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getCep() {
        return this.cep;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public int getNumero() {
        return this.numero;
    }
}
