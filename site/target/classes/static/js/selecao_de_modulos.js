const modulos_btns = Array.from(document.getElementsByClassName("modulo"));
const salvar_btn = document.getElementById("salvar");
const tituloModal = document.getElementById("tituloModal");
const conteudoModal = document.getElementById("conteudoModal");
const modal = new bootstrap.Modal(document.getElementById("meuModal"));

salvar_btn.onclick = () => {
    // A DEFINIR
}

//configura o comportamento das seleções
modulos_btns.forEach(e => {
    e.onclick = () => {
        
        if(e.classList.contains("nao_selecionado")){
            e.classList.remove("nao_selecionado");
            e.classList.add("selecionado");
        } else {
            e.classList.remove("selecionado");
            e.classList.add("nao_selecionado");
        }

    }
});
