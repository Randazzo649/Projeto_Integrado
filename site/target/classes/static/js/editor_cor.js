const areaCor = document.getElementById("area_cor");
const seletorCor = document.getElementById("seletor_cor");
const barraMatriz = document.getElementById("barra_matriz");
const seletorMatriz = document.getElementById("seletor_matriz");
const hex = document.getElementById("hex");
const rgb = document.getElementById("rgb");
const hsl = document.getElementById("hsl");
const header =document.getElementById("superior");
const corInicial = rgbParaHue(header.style.backgroundColor);
let hue = corInicial.h;
let saturation = corInicial.s;
let value = corInicial.v;
// variaveis para upload de arquivo
const file_in = document.getElementById("file_in");
const file_btn = document.getElementById("alterar_logo_btn");
const salvar_btn = document.getElementById("salvar_btn");
//variaveis controle foto
const logo_img = document.getElementById("logo");
//modal
const modal = new bootstrap.Modal(document.getElementById("meuModal"));
const tituloModal = document.getElementById("tituloModal");
const conteudoModal = document.getElementById("conteudoModal");

//comunicação com o back-end
salvar_btn.onclick = () => {

    const foto = file_in.files[file_in.files.length - 1];
    const cor = "rgb(" + rgb.value + ")";

    const dados = new FormData();
    dados.append("cor", cor);
    dados.append("foto", foto);

    fetch(URL_SITE + "/empresa/salvar_alteracoes", {
        method : "POST",
        body : dados
    }).then(r => {return r.text()}).then(r => {
        if(r === "1"){
            tituloModal.innerHTML = "Sucesso!";
            conteudoModal.innerHTML = "alterações salvas com sucesso";
        } else {
            tituloModal.innerHTML = "Ops...";
            conteudoModal.innerHTML = "Não foi possivel salvar estas alterações, se o problema persistir, entre em contato com a equipe de suporte.";
        }
        modal.show();
    })
}

//configuração do upload de arquivos
file_btn.onclick = () => {
    file_in.click();
}
file_in.onchange = () => {
    file_btn.style.backgroundColor = "rgb(145, 250, 145)";
    file_btn.style.border = " 1px solid rgb(128, 248, 128)";

    if (file_in.files.length === 0) return;

    const url_nova_foto = URL.createObjectURL(file_in.files[file_in.files.length - 1]);
    logo_img.src = url_nova_foto;
}

//configuração da area de cor
areaCor.addEventListener(
    "mousedown",
    function (event){
        escolherCor(event);
        function mover(mouseEvent) {
            escolherCor(mouseEvent);
        }
        function parar() {
            document.removeEventListener(
                "mousemove",
                mover
            );
            document.removeEventListener(
                "mouseup",
                parar
            );
        }
        document.addEventListener(
            "mousemove",
            mover
        );
        document.addEventListener(
            "mouseup",
            parar
        );
    }
);
barraMatriz.addEventListener(
    "mousedown",
    function (event) {
        escolherMatriz(event);
        function mover(mouseEvent) {
            escolherMatriz(mouseEvent);
        }
        function parar() {
            document.removeEventListener(
                "mousemove",
                mover
            );
            document.removeEventListener(
                "mouseup",
                parar
            );
        }
        document.addEventListener(
            "mousemove",
            mover
        );
        document.addEventListener(
            "mouseup",
            parar
        );
    }
);

function escolherCor(event){
    const rect = areaCor.getBoundingClientRect();
    let x = event.clientX - rect.left;
    let y = event.clientY - rect.top;
    x = Math.max(0, Math.min(x, rect.width));
    y = Math.max(0, Math.min(y, rect.height));
    saturation = x / rect.width;
    value = 1 - (y / rect.height);
    seletorCor.style.left = x + "px";
    seletorCor.style.top = y + "px";
    atualizarCor();
}

function escolherMatriz(event){
    const rect = barraMatriz.getBoundingClientRect();
    let x = event.clientX - rect.left;
    x = Math.max(0, Math.min(x, rect.width));
    hue = (x / rect.width) * 360;
    seletorMatriz.style.left = x + "px";
    areaCor.style.setProperty("--hue", hue);
    atualizarCor();
}

function atualizarCor(){
    const cor = hsvParaRgb(hue, saturation, value);
    const r = cor.r;
    const g = cor.g;
    const b = cor.b;
    const corSelecionada = `rgb(${r}, ${g}, ${b})`;
    seletorCor.style.backgroundColor = corSelecionada;
    const corMatriz = `hsl(${hue}, 100%, 50%)`;
    seletorMatriz.style.backgroundColor = corMatriz;
    hex.value = rgbParaHex(r, g, b);
    rgb.value = `${r}, ${g}, ${b}`;
    const hslCor = rgbParaHsl(r, g, b);
    hsl.value = `${Math.round(hslCor.h)}°, ` + `${Math.round(hslCor.s)}%, ` + `${Math.round(hslCor.l)}%`;
    header.style.backgroundColor = "rgb("+r+","+g+","+b+")";
}

function hsvParaRgb(h, s, v){
    let r, g, b;
    const i = Math.floor(h / 60);
    const f = h / 60 - i;
    const p = v * (1 - s);
    const q = v * (1 - f * s);
    const t = v * (1 -(1 - f) * s);
    switch (i % 6) {
        case 0:
            r = v;
            g = t;
            b = p;
            break;
        case 1:
            r = q;
            g = v;
            b = p;
            break;
        case 2:
            r = p;
            g = v;
            b = t;
            break;
        case 3:
            r = p;
            g = q;
            b = v;
            break;
        case 4:
            r = t;
            g = p;
            b = v;
            break;
        case 5:
            r = v;
            g = p;
            b = q;
            break;
    }
    return {
        r: Math.round(r * 255),
        g: Math.round(g * 255),
        b:Math.round(b * 255)
    };
}

function rgbParaHex(r, g, b){
    return "#" + [r, g, b].map(valor => valor.toString(16).padStart(2, "0")).join("").toUpperCase();
}

function rgbParaHsl(r, g, b){
    r /= 255;
    g /= 255;
    b /= 255;
    const maior = Math.max( r, g, b);
    const menor = Math.min(r, g, b);
    let h;
    let s;
    const l = (maior + menor) / 2;
    if (maior === menor){
        h = 0;
        s = 0;
    }
    else {
        const diferenca = maior - menor;
        s = l > 0.5 ? diferenca / (2 - maior - menor) : diferenca / (maior + menor);
        switch (maior){
            case r:
                h = ((g - b) / diferenca) + (g < b ? 6 : 0);
                break;
            case g:
                h = ((b - r) / diferenca) + 2;
                break;
            case b:
                h = ((r - g) / diferenca) + 4;
                break;
        }
        h /= 6;
    }
    return {
        h: h * 360,
        s: s * 100,
        l: l * 100
    };
}

// rgbString = rgb(n,n,n)
function rgbParaHue(rgbString){
    
    rgbString = rgbString.replace("rgb(", "");
    rgbString = rgbString.replace(")", "");

    const rgb = rgbString.split(",");

    let r = parseInt(rgb[0]) / 255;
    let g = parseInt(rgb[1]) / 255;
    let b = parseInt(rgb[2]) / 255;

    const maior = Math.max(r, g, b);
    const menor = Math.min(r, g, b);
    const diferenca = maior - menor;

    let h = 0;
    let s = 0;
    let v = maior;

    if(maior !== 0)
        s = diferenca / maior;

    if(diferenca !== 0){

        switch(maior){
            case r:
                h = ((g - b) / diferenca) % 6;
                break;

            case g:
                h = ((b - r) / diferenca) + 2;
                break;

            case b:
                h = ((r - g) / diferenca) + 4;
                break;
        }

        h *= 60;

        if(h < 0)
            h += 360;
    }

    return {
        h: h,
        s: s,
        v: v
    };
}

areaCor.style.setProperty("--hue", hue);
seletorMatriz.style.left = ((hue / 360) * 100) + "%";
seletorCor.style.left = "100%";
seletorCor.style.top = "0%";
atualizarCor();