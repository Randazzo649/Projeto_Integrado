package com.sistema.models;


public class Funcionario {
    private long id;
    private String nome;
    private String email;
    private String senha;
    private String cargo;
    private String salario;
    private String data_admissao;
    private String data_nascimento;
    private String estado;
    private String endereco;
    private String cpf;
    private String telefone;
    private String genero;
    private long idEmpresa;
    private long idDepartamento;
    //o atributo abaixo não deve ser carregado do banco
    //só será usado quando necessário em views
    private String nomeDepartamento;

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
    public String getData_nascimento() {
        return data_nascimento;
    }
    public String getGenero() {
        return genero;
    }
    public String getTelefone() {
        return telefone;
    }
    public String getNomeDepartamento() {
        return nomeDepartamento;
    }
    public String getCpf() {
        return cpf;
    }
    public String getEndereco() {
        return endereco;
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
    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public void setNomeDepartamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
