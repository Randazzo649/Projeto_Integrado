const formulario = document.getElementById("formulario_perfil");
const campoFoto = document.getElementById("foto_perfil");

const areaUpload = document.getElementById("area_upload");
const conteudoUpload = document.getElementById("conteudo_upload");
const textoUpload = document.querySelector(".texto-upload");
const previewFoto = document.getElementById("preview_foto");
const btnRemoverFoto = document.getElementById("remover_foto");

let urlFoto = null;

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

    const extensaoValida = /\.(png|svg|pdf)$/i.test(arquivo.name);

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