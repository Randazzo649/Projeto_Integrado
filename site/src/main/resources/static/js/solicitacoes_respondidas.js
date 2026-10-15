
function excluir (id){  
    const fd = new FormData();
    fd.append("id", id);
    fetch(URL_SITE + "/curadoria/deletar_solicitacao", {
        method : "POST",
        body : fd
    }).then(r => { return r.text() }).then(r => {
        if( r === "1")
            window.location.reload();
    })
}