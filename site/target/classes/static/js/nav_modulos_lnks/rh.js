const cadastro_func_lnk = document.getElementById("quadro_funcionarios_btn");
const relatorios_func_lnk = document.getElementById("funcionarios_relatorio_btn");

cadastro_func_lnk.onclick = () => {
    window.location.href = URL_SITE + "/rh/cadastro_funcionario";
}

relatorios_func_lnk.onclick = () => {
    window.location.href = URL_SITE + "/rh/relatorio_funcionarios";
}