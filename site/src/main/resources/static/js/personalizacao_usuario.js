const formulario = document.getElementById("formulario_perfil");
const campoFoto = document.getElementById("foto_perfil");

const areaUpload = document.getElementById("area_upload");
const conteudoUpload = document.getElementById("conteudo_upload");
const textoUpload = document.querySelector(".texto-upload");
const previewFoto = document.getElementById("preview_foto");
const btnRemoverFoto = document.getElementById("remover_foto");
//botão de salvar
const salvar_btn = document.getElementById("salvar_btn");
//inputs
const nome_in = document.getElementById("nome");
const funcao_in = document.getElementById("funcao");
const email_in = document.getElementById("email");
const senha_in = document.getElementById("senha");
const foto_in = document.getElementById("foto_perfil");
//variaveis do modal
const tituloModal = document.getElementById("tituloModal");
const conteudoModal = document.getElementById("conteudoModal");
const modal = new bootstrap.Modal(document.getElementById("meuModal"));
//outras
const usuario_foto_img = document.getElementById("usuario_foto_img");

salvar_btn.onclick = () => {
    const fd = new FormData();
    fd.append("nome", nome_in.value);
    fd.append("funcao", funcao_in.value);
    fd.append("email", email_in.value);
    fd.append("senha", senha_in.value);
    fd.append("foto", foto_in.files[foto_in.files.length - 1]);

    fetch(URL_SITE+"/usuarios/edicao", {
        method : "POST",
        body : fd
    }).then( r => {return r.text()}).then(r => {
        if (r == "1"){
            tituloModal.innerHTML = "Sucesso";
            conteudoModal.innerHTML = "alterações salvas com sucesso";
        } else {
            tituloModal.innerHTML = "Ops...";
            conteudoModal.innerHTML = "Não foi possivel salvar as alterações, verifique os dados inseridos, se o problema persistir entre em contato com a equipe de suporte";
        } 
        modal.show();
    })
}


//configuração do resto do site
foto_in.onchange = () => {
    const foto_url = URL.createObjectURL(foto_in.files[foto_in.files.length - 1]);
    usuario_foto_img.src = foto_url;
}

function limparFoto() {
    if (urlFoto) {
        URL.revokeObjectURL(urlFoto);
        urlFoto = null;
    }

    campoFoto.value = "";
    previewFoto.removeAttribute("src");
    previewFoto.hidden = true;
    conteudoUpload.hidden = false;
    textoUpload.textContent =
        "Clique ou Arraste e solte os seus arquivos aqui";

    btnRemoverFoto.hidden = true;
}

function mostrarArquivo(arquivo) {
    if (!arquivo) return;

    const extensaoValida = /\.(png|jpg|jpeg)$/i.test(arquivo.name);

    if (!extensaoValida) {
        alert("Selecione um arquivo PNG, SVG ou PDF.");
        limparFoto();
        return;
    }

    if (urlFoto) {
        URL.revokeObjectURL(urlFoto);
        urlFoto = null;
    }

    btnRemoverFoto.hidden = false;

    const ehPDF = arquivo.name.toLowerCase().endsWith(".pdf");

    if (ehPDF) {
        previewFoto.removeAttribute("src");
        previewFoto.hidden = true;
        conteudoUpload.hidden = false;
        textoUpload.textContent = "Arquivo selecionado: " + arquivo.name;
        return;
    }

    urlFoto = URL.createObjectURL(arquivo);

    previewFoto.src = urlFoto;
    previewFoto.hidden = false;
    conteudoUpload.hidden = true;
}

campoFoto.addEventListener("change", () => {
    mostrarArquivo(campoFoto.files[0]);
});

areaUpload.addEventListener("dragover", (evento) => {
    evento.preventDefault();
    areaUpload.classList.add("arrastando");
});

areaUpload.addEventListener("dragleave", () => {
    areaUpload.classList.remove("arrastando");
});

areaUpload.addEventListener("drop", (evento) => {
    evento.preventDefault();
    areaUpload.classList.remove("arrastando");

    const arquivo = evento.dataTransfer.files[0];

    if (!arquivo) return;

    const transferencia = new DataTransfer();
    transferencia.items.add(arquivo);
    campoFoto.files = transferencia.files;

    mostrarArquivo(arquivo);
});

btnRemoverFoto.addEventListener("click", (evento) => {
    evento.preventDefault();
    evento.stopPropagation();
    limparFoto();
});

formulario.addEventListener("reset", () => {
    setTimeout(() => {
        limparFoto();
    }, 0);
});

window.addEventListener("beforeunload", () => {
    if (urlFoto) {
        URL.revokeObjectURL(urlFoto);
    }
});