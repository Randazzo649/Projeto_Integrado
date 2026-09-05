package com.sistema.models;

public class Usuario {
    
    private long id;
    private String nome;
    private String email;
    private String senha;
    private String foto;
    private boolean curador;
    
    
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
        return nome;
    }
    public String getFoto() {
        return foto;
    }
    public boolean isCurador() {
        return curador;
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
}
