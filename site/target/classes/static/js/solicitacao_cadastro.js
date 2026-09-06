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

salvar_btn.onclick = () => {
    const fd = new FormData();
    fd.append("razao", razao_social_in.value);
    fd.append("cnpj",cnpj_in.value);
    fd.append("telefone",telefone_in.value);
    fd.append("endereco",endereco_in.value);
    fd.append("email",email_in.value);
    fd.append("senha",senha_in.value);
    fd.append("nome",nome_in.value);
    fd.append("file", file_in[0]);
    fetch(URL_SITE + "/Empresa/Cadastro", {
        method: "POST",
        data: fd,
        header: {
            "Content-Type": "multipart/form-data"
        }
    })
    .then(r => {return r.text()})
    .then(r => {
        
    })
}

file_in_btn.onclick = () => {
    file_in.click();
}

