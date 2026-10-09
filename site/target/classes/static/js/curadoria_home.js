const novas_solics_lnk_btn = document.getElementById("link_novas_solics");
const btn_remocao = document.getElementById("remocao");

novas_solics_lnk_btn.onclick = () => {
    window.location.href = URL_SITE + "/curadoria/solicitacoes";
}

btn_remocao.onclick = () => {
    window.location.href = URL_SITE + "/curadoria/empresas";
}