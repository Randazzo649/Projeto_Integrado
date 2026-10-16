const nome_input = document.getElementById("nome_input");
const tipo_input = document.getElementById("tipo_input");
const preco_input = document.getElementById("preco_input");
const quantidade_input = document.getElementById("quantidade_input");
const Unidae_input = document.getElementById("Unidae_input");
const material_input = document.getElementById("material_input");
const tamanho_input = document.getElementById("tamanho_input");
const cor_input = document.getElementById("cor_input");
const estoque_input = document.getElementById("estoque_input");
const descricao_input = document.getElementById("descricao_input");
const fotos_input = document.getElementById("fotos_input");
//modal
const modal = new bootstrap.Modal(document.getElementById("meuModal"));
const tituloModal = document.getElementById("tituloModal");
const conteudoModal = document.getElementById("conteudoModal");

const salvar_btn = document.getElementById("salvar_btn");


salvar_btn.onclick = () => {

    const fd = new FormData();

    fd.append( "nome", nome_input.value);
    fd.append( "tipo", tipo_input.value);
    fd.append( "preco", preco_input.value);
    fd.append( "quantidade", quantidade_input.value);
    fd.append( "Unidae", Unidae_input.value);
    fd.append( "material", material_input.value);
    fd.append( "tamanho", tamanho_input.value);
    fd.append( "cor", cor_input.value);
    fd.append( "estoque", estoque_input.value);
    fd.append( "descricao", descricao_input.value);
    fd.append( "fotos", fotos_input.files[fotos_input.files.length - 1]);

    fetch(URL_SITE + "/estoque/produto_cadastro", {
        method : "POST",
        body : fd
    }).then(r => {return r.text()}).then(r => {
        if(r === "1"){
            tituloModal.innerHTML = "Sucesso";
            conteudoModal.innerHTML = "Produto cadastrado com sucesso";
        } else {
            tituloModal.innerHTML = "Ops...";
            conteudoModal.innerHTML = "houve um erro ao cadastrar produto, verifique os dados ou tente mais tarde, se o problema persistir informe a equipe de suporte";
        }
        modal.show();
    });

}