const nome = document.getElementById("nome_input");
const data_nasc = document.getElementById("data_nascimento_input");
const cpf = document.getElementById("cpf_input");
const genero = document.getElementById("genero_input");
const telefone = document.getElementById("telefone_input");
const endereco = document.getElementById("endereco_input");
const data_adm = document.getElementById("data_admissao_input");
const cargo = document.getElementById("cargo_input");
const departamento = document.getElementById("departamento_input");
const salario = document.getElementById("salario_input");
const email = document.getElementById("email_input");
const senha = document.getElementById("senha_input");

salvar_btn.onclick = () => {
    const fd = new FormData();
    fd.append(nome);
    fd.append(data_nasc);
    fd.append(cpf);
    fd.append(genero);
    fd.append(telefone);
    fd.append(endereco);
    fd.append(data_adm);
    fd.append(cargo);
    fd.append(departamento);
    fd.append(salario);
    fd.append(email);
    fd.append(senha);
}