package com.sistema.models;


public class Funcionario {
    private long id;
    private String nome;
    private String email;
    private String senha;
    private String cargo;
    private String salario;
    private String data_admissao;
    private String estado;
    private long idEmpresa;
    private long idDepartamento;

    public long getId() {
        return id;
    }
    public String getCargo() {
        return cargo;
    }
    public String getData_admissao() {
        return data_admissao;
    }
    public String getEmail() {
        return email;
    }
    public String getEstado() {
        return estado;
    }
    public long getIdDepartamento() {
        return idDepartamento;
    }
    public long getIdEmpresa() {
        return idEmpresa;
    }
    public String getNome() {
        return nome;
    }
    public String getSalario() {
        return salario;
    }
    public String getSenha() {
        return senha;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public void setData_admissao(String data_admissao) {
        this.data_admissao = data_admissao;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setIdDepartamento(long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }
    public void setIdEmpresa(long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setSalario(String salario) {
        this.salario = salario;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
