const experimente_btn = document.getElementById("experimente");
const aviso_erro_login = document.getElementById("aviso");
const entrar_btn = document.getElementById("entrar");
const email_in = document.getElementById("email_in");
const senha_in = document.getElementById("senha_in");

entrar_btn.onclick = () => {

    const email = email_in.value;
    const senha = senha_in.value;
    const l = [ email_in, senha_in ];
    let proseguir = true;
    l.forEach(e => {
        if(e.value.trim() === "" || e.value == null){
            e.setCustomValidity("Campo obrigatorio");
            e.reportValidity();
            proseguir = false;
            return;
        } else {
            e.setCustomValidity("Campo obrigatorio");
        }
    })

    if(proseguir) {

        const fd = new FormData();
        fd.append("email", email);
        fd.append("senha", senha);

        fetch(URL_SITE + "/usuarios/login", {
            method : "POST", 
            body : fd
        }).then(r => {return r.text()}).then(r => {
            if(r === "0"){
                aviso_erro_login.innerHTML = "Email ou senha errados";
                return;
            }
            window.location.href = URL_SITE + r;
        });
    }

}


experimente_btn.onclick = () => {
    window.location.href = URL_SITE + "/solicitacao_cadastro/dados"
}