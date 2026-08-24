package com.sistema.percistence.models;

public class Usuario {
    private long id;
    private String email;
    private String senha;
    public Usuario(){
        this.id = 0;
        this.email = "";
        this.senha = "";
    }
    public void setId(long id){
        this.id = id;
    }
    public long getId(){
        return this.id;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }
    public String getSenha(){
        return this.senha;
    }
}
