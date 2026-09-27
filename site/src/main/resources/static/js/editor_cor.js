const areaCor = document.getElementById("area_cor");
const seletorCor = document.getElementById("seletor_cor");
const barraMatriz = document.getElementById("barra_matriz");
const seletorMatriz = document.getElementById("seletor_matriz");
const hex = document.getElementById("hex");
const rgb = document.getElementById("rgb");
const hsl = document.getElementById("hsl");
let hue = 356;
let saturation = 1;
let value = 1;

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

areaCor.style.setProperty("--hue", hue);
seletorMatriz.style.left = ((hue / 360) * 100) + "%";
seletorCor.style.left = "100%";
seletorCor.style.top = "0%";
atualizarCor();