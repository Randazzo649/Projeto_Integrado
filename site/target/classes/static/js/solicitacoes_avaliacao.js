const URL_AVAL = URL_SITE + "/empresa/avaliacao_aprovacao";
const URL_REJ = URL_SITE + "/empresa/avaliacao_rejeicao";

function aprovar( id ){
    const dados = new FormData()
    dados.append("id", id);
    fetch( URL_AVAL, {
        method : "POST",
        body : dados
    }).then(r => { return r.text() }).then(r => {
        if(r === "1")
            document.getElementById("solic_"+id).remove();
    })
}


function rejeitar ( id ){
    const dados = new FormData()
    dados.append("id", id);
    fetch( URL_REJ, {
        method : "POST",
        body : dados
    }).then(r => { return r.text() }).then(r => {
        if(r === "1")
            document.getElementById("solic_"+id).remove();
    })
}