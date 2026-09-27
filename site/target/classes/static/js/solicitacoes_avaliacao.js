const URL_AVAL = URL_SITE + "/empresa/avaliacao"

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
    
}