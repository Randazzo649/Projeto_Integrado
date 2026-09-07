package com.sistema.models;

public class Solicitacao {
    private long id;
    private String nome;
    private String razaoSocial;
    private String cnpj;
    private String telefone;
    private String endereco;
    private String email;
    private String senha;
    private String dataSolicitacao;
    private String dataDecisao;
    private String documento;

    
    public String getDocumento() {
        return documento;
    }
    public String getCnpj() {
        return cnpj;
    }
    public String getDataDecisao() {
        return dataDecisao;
    }
    public String getDataSolicitacao() {
        return dataSolicitacao;
    }
    public String getEmail() {
        return email;
    }
    public String getEndereco() {
        return endereco;
    }
    public long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getRazaoSocial() {
        return razaoSocial;
    }
    public String getSenha() {
        return senha;
    }
    public String getTelefone() {
        return telefone;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public void setDataDecisao(String dataDecisao) {
        this.dataDecisao = dataDecisao;
    }
    public void setDataSolicitacao(String dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }
}


