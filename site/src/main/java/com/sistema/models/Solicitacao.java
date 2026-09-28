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
    private boolean aprovada;

    private Modulo[] modulos;

    
    public String getDocumento() {
        return this.documento;
    }
    public String getCnpj() {
        return this.cnpj;
    }
    public String getDataDecisao() {
        return this.dataDecisao;
    }
    public String getDataSolicitacao() {
        return this.dataSolicitacao;
    }
    public String getEmail() {
        return this.email;
    }
    public String getEndereco() {
        return this.endereco;
    }
    public long getId() {
        return this.id;
    }
    public String getNome() {
        return this.nome;
    }
    public String getRazaoSocial() {
        return this.razaoSocial;
    }
    public String getSenha() {
        return this.senha;
    }
    public String getTelefone() {
        return this.telefone;
    }
    public Modulo[] getModulos() {
        return modulos;
    }
    public boolean isAprovada() {
        return aprovada;
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
    public void setModulos(Modulo[] modulos) {
        this.modulos = modulos;
    }
    
    public void setAprovada(boolean aprovada) {
        this.aprovada = aprovada;
    }
}


