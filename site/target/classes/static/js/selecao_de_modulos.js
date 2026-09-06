const modulos_btns = Array.from(document.getElementsByClassName("modulo"));



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
