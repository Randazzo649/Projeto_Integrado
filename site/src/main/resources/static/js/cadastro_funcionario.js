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
//modal
const modal = new bootstrap.Modal(document.getElementById("meuModal"));
const tituloModal = document.getElementById("tituloModal");
const conteudoModal = document.getElementById("conteudoModal");

salvar_btn.onclick = () => {
    const fd = new FormData();
    fd.append("nome", nome.value);
    fd.append("data_nasc", data_nasc.value);
    fd.append("cpf", cpf.value);
    fd.append("genero", genero.value);
    fd.append("telefone", telefone.value);
    fd.append("endereco", endereco.value);
    fd.append("data_adm", data_adm.value);
    fd.append("cargo", cargo.value);
    fd.append("departamento", departamento.value);
    fd.append("salario", salario.value);
    fd.append("email", email.value);
    fd.append("senha", senha.value);

    fetch(URL_SITE + "/rh/cadastrar_funcionario",{
        method : "POST",
        body : fd
    }).then(r => {return r.text()}).then(r => {
        if(r === "1"){
            tituloModal.innerHTML = "Sucesso!";
            conteudoModal.innerHTML = "novo funcionário cadastrado com sucesso";
        } else {
            tituloModal.innerHTML = "Ops...";
            conteudoModal.innerHTML = "Não foi possivel cadastrar este funcionário, verifique os dados inseridos, se o problema persistir, entre em contato com a equipe de suporte.";
        }
        modal.show();
    });
}