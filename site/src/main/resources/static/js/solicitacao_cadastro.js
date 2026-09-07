const cnpj_in = document.getElementById("cnpj_in");
const razao_social_in = document.getElementById("razao");
const nome_in = document.getElementById("nome_fantasia_in");
const telefone_in = document.getElementById("telefone_in");
const endereco_in = document.getElementById("endereco_in");
const email_in = document.getElementById("email_in");
const senha_in = document.getElementById("senha_in");
const file_in = document.getElementById("file_in");
const file_in_btn = document.getElementById("file_in_btn");
const add_funcs_btn = document.getElementById("add_funcs_btn");
const salvar_btn = document.getElementById("salvar_btn");
const modal = new bootstrap.Modal(document.getElementById("meuModal"));
const tituloModal = document.getElementById("tituloModal");
const conteudoModal = document.getElementById("conteudoModal");


salvar_btn.onclick = () => {
    //validação
    const ins = [razao_social_in, cnpj_in, telefone_in, endereco_in, email_in, senha_in, nome_in, file_in];
    ins.forEach(e => {
        if(e.value.trim() === "" || e.value == null){
            e.setCustomValidity("Este campo é obrigatório");
            e.reportValidity();
            return;
        } else {
            e.setCustomValidity("");
        }
    })
    if(file_in.files.length === 0){
        file_in_btn.classList.add("vazio");
        return
    }
    //envio
    const fd = new FormData();
    fd.append("razao", razao_social_in.value);
    fd.append("cnpj",cnpj_in.value);
    fd.append("telefone",telefone_in.value);
    fd.append("endereco",endereco_in.value);
    fd.append("email",email_in.value);
    fd.append("senha",senha_in.value);
    fd.append("nome",nome_in.value);
    fd.append("file", file_in.files[file_in.files.length - 1]);

    fetch(URL_SITE + "/empresa/solicitacao", {
        method: "POST",
        body: fd,
        header: {"Content-Type": "multipart/form-data"}
    }).then(r => {return r.text()}).then(r => {
        if (r === "1"){
            tituloModal.innerHTML = "Sucesso !";
            conteudoModal.innerHTML = "Sua solicitação foi registrada com sucesso, enviaremos uma resposta para seu E-mail";
        } else {
            tituloModal.innerHTML = "Ops...";
            conteudoModal.innerHTML = "Sua solicitação não pode ser registrada, se você já solicitou acesso a nossos serviços espere nossa resposta em seu email, caso contrário tente mais tarde";
        }
        modal.show();
    })
}

file_in_btn.onclick = () => {
    file_in.click();
}

cnpj_in.oninput = () => {
    //tudo que não for numero sai
    let valor = cnpj_in.value.replace(/\D/g, "").slice(0, 14);
    //impoe a formatacao
    if (valor.length > 2)
        valor = valor.slice(0, 2) + "." + valor.slice(2);
    if (valor.length > 6)
        valor = valor.slice(0, 6) + "." + valor.slice(6);
    if (valor.length > 10)
        valor = valor.slice(0, 10) + "/" + valor.slice(10);
    if (valor.length > 15)
        valor = valor.slice(0, 15) + "-" + valor.slice(15);
    //so poe o que estiver no formato
    cnpj_in.value = valor;
}

telefone_in.oninput = () => {
    let valor = telefone_in.value.replace(/\D/g, "").slice(0, 13);

    if (valor.length > 0)
        valor = "+" + valor;
    if (valor.length > 3)
        valor = valor.slice(0, 3) + " (" + valor.slice(3);
    if (valor.length > 7)
        valor = valor.slice(0, 7) + ") " + valor.slice(7);
    if (valor.length > 13)
        valor = valor.slice(0, 14) + "-" + valor.slice(14);

    telefone_in.value = valor;
};

file_in.oninput = () => {
    file_in_btn.classList.remove("vazio");
    file_in_btn.classList.add("nao_vazio");
}