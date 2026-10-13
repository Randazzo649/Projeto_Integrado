const URL_AVAL = URL_SITE + "/empresa/avaliacao_aprovacao";
const URL_REJ = URL_SITE + "/empresa/avaliacao_rejeicao";

function aprovar( id ){
    const dados = new FormData();
    const form_div = document.getElementById("collapse" + id);
    dados.append("id", id);
    
    form_div.innerHTML = "<div class=\"d-flex w-100 justify-content-center p-5\">"+
                                "<img src=\""+URL_SITE+"/imgs/carregamento.gif\" style=\"width:100px; height:100px;\">"+
                         "</div>";

    fetch( URL_AVAL, {
        method : "POST",
        body : dados
    }).then(r => { return r.text() }).then(r => {
        if(r === "1")
            document.getElementById("solic_"+id).remove();
    })
}


function rejeitar ( id ){
    const dados = new FormData();
    const form_div = document.getElementById("collapse" + id);

    dados.append("id", id);

    form_div.innerHTML = "<div class=\"d-flex w-100 justify-content-center p-5\">"+
                                "<img src=\""+URL_SITE+"/imgs/carregamento.gif\" style=\"width:100px; height:100px;\">"+
                         "</div>";

    fetch( URL_REJ, {
        method : "POST",
        body : dados
    }).then(r => { return r.text() }).then(r => {
        if(r === "1")
            document.getElementById("solic_"+id).remove();
    })
}