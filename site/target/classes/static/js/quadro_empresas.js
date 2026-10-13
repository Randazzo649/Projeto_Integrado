const btn_voltar = document.getElementById("voltar");
const btn_desativar = document.getElementById("desativar");
//modal
const modal = new bootstrap.Modal(document.getElementById("meuModal"));
const tituloModal = document.getElementById("tituloModal");
const conteudoModal = document.getElementById("conteudoModal");

btn_voltar.onclick = () => {
    window.location.href = URL_SITE + "/curadoria/home";
}

function desativarEmpresa(id) {
    const formData = new FormData();
    formData.append("id", id);
    fetch(URL_SITE + "/empresa/desativar", {
        method: "POST",
        body: formData
    }).then(response => { return response.text() }).then(r => {
        if(r === "1"){
            tituloModal.innerHTML = "Sucesso!";
            conteudoModal.innerHTML = "empresa desativada com sucesso";
        } else {
            tituloModal.innerHTML = "Ops...";
            conteudoModal.innerHTML = "Não foi possivel desativar esta empresa, verifique os dados inseridos, se o problema persistir, entre em contato com a equipe de suporte.";
        }
        modal.show();
    })
}
