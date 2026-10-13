//variaveis modal
const tituloModal = document.getElementById("tituloModal");
const conteudoModal = document.getElementById("conteudoModal");
const modal = new bootstrap.Modal(document.getElementById("meuModal"));
//links
const cadastrar_func_lnk = document.getElementById("registrar_funcionario");


cadastrar_func_lnk.onclick = () => {
    window.location.href = URL_SITE + "/rh/cadastro_funcionario";
}

function salvarEstadoAtual( id ){
    const nome = document.getElementById("nome_" + id).value;
    const data_nascimento = document.getElementById("data_nascimento_" + id).value;
    const cpf = document.getElementById("cpf_" + id).value;
    const genero = document.getElementById("genero_" + id).value;
    const telefone = document.getElementById("telefone_" + id).value;
    const endereco = document.getElementById("endereco_" + id).value;
    const data_admissao = document.getElementById("data_admissao_" + id).value;
    const cargo = document.getElementById("cargo_" + id).value;
    const departamento = document.getElementById("departamento_" + id).value;
    const salario = document.getElementById("salario_" + id).value;
    const email = document.getElementById("email_" + id).value;

    const fd = new FormData();
    fd.append("nome", nome);
    fd.append("data_nascimento", data_nascimento);
    fd.append("cpf", cpf);
    fd.append("genero", genero);
    fd.append("telefone", telefone);
    fd.append("endereco", endereco);
    fd.append("data_admissao", data_admissao);
    fd.append("cargo", cargo);
    fd.append("departamento", departamento);
    fd.append("salario", salario);
    fd.append("email", email);
    fd.append("id", id);

    fetch(URL_SITE + "/rh/salvar_estado_atual", {
        method : "POST",
        body : fd
    }).then(r => { return r.text() }).then(r => {
        if (r === "1"){
            tituloModal.innerHTML = "Sucesso";
            conteudoModal.innerHTML = "Dados de "+ nome +" alterados com sucesso";
        } else {
            tituloModal.innerHTML = "Ops...";
            conteudoModal.innerHTML = "Falha ao alterar dados do usuário, verifique os dados inserido, se o problema persistir tente mais tarde ou entre em contato com a equipe de suporte";
        }
        modal.show();
    });

}

function remover( id ){

    const nome = document.getElementById("nome_" + id).value;

    const fd = new FormData();
    fd.append("id", id);

    fetch(URL_SITE + "/rh/remover", {
        method : "POST",
        body : fd
    }).then(r => { return r.text() }).then(r => {
        if (r === "1"){
            tituloModal.innerHTML = "Sucesso";
            conteudoModal.innerHTML = "Registro de " + nome + " removido com sucesso ";
        } else {
            tituloModal.innerHTML = "Ops...";
            conteudoModal.innerHTML = "Falha ao remover registro do usuário, tente mais tarde, se o problema persistir tente mais tarde ou entre em contato com a equipe de suporte";
        }
        modal.show();
    });


}

//configuração dos inputs
function formatarCpf( id ){
    const cpf = document.getElementById("cpf_" + id);
    let valor = cpf.value.replace(/\D/g, "").slice(0, 11);

    if (valor.length > 3)
        valor = valor.slice(0, 3) + "." + valor.slice(3);
    if (valor.length > 7)
        valor = valor.slice(0, 7) + "." + valor.slice(7);
    if (valor.length > 11)
        valor = valor.slice(0, 11) + "-" + valor.slice(11);

    cpf.value = valor;

}

function formatarTelefone( id ){
    const telefone = document.getElementById("telefone_" + id);
    let valor = telefone.value.replace(/\D/g, "").slice(0, 13);

    if (valor.length > 0)
        valor = "+" + valor;
    if (valor.length > 3)
        valor = valor.slice(0, 3) + " (" + valor.slice(3);
    if (valor.length > 7)
        valor = valor.slice(0, 7) + ") " + valor.slice(7);
    if (valor.length > 13)
        valor = valor.slice(0, 14) + "-" + valor.slice(14);

    telefone.value = valor;
}
