package com.sistema.models;

public class Usuario {
    
    private long id;
    private String nome;
    private String email;
    private String senha;
    private String foto;
    private boolean curador;
    private String funcao;
    private long idEmpresa;
    private boolean ativo;
    
    public long getId(){
        return this.id;
    }
    public String getEmail(){
        return this.email;
    }
    public String getSenha(){
        return this.senha;
    }
    public String getNome() {
       return this.nome;
    }
    public String getFoto() {
       return this.foto;
    }
    public boolean isCurador() {
       return this.curador;
    }
    public boolean isAtivo() {
        return ativo;
    }
    public String getFuncao() {
        return funcao;
    }
    public long getIdEmpresa() {
        return idEmpresa;
    }
    
    public void setId(long id){
        this.id = id;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }
    public void setCurador(boolean curador) {
        this.curador = curador;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
    public void setIdEmpresa(long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
}
