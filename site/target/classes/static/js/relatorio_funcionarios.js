const relatorio_download_btn = document.getElementById("exportar_pdf");

const funcionario_departamento_canvas = document.getElementById("grafico_departamentos");
const percentual_funcionarios_situacao_canvas = document.getElementById("grafico_situacao");

relatorio_download_btn.onclick = () => {
    const a = document.createElement("a");
    a.href = URL_SITE + "/rh/relatorio";
    a.download = "relatorio-rh.pdf";
    a.click();
    a.remove();
}


//configuração dos componentes visuais

//grafico funcionarios por departamento

//a variavel departamento vem pelo thymeleaf
new Chart(funcionario_departamento_canvas, {
    type: 'bar',
    data: {
        labels: Object.keys(departamentos),
        datasets: [{
            label: 'Numero de Funcionários',
            data: Object.values(departamentos)
        }]
    },
    options: {
        indexAxis: 'y'
    }
});

//grafico percentual de funcionarios por situacao
const funcionarios_situacao = {
    "ativos": ativo,
    "inativos": inativo,
    "ferias": ferias,
    "licenca": licenca
};
new Chart(percentual_funcionarios_situacao_canvas, {
    type: 'pie',
    data: {
        labels: Object.keys(funcionarios_situacao),
        datasets: [{
            data: Object.values(funcionarios_situacao)
        }]
    }
});


//calendario

const admissao_span = Array.from(document.getElementsByClassName("admissao_span"));
const admissoes = [];
admissao_span.forEach(e => {
    admissoes.push(e.innerHTML)
})

const desligamentos = [];

document.addEventListener("DOMContentLoaded", () => {

    inicializarCalendario();

});

function inicializarCalendario() {

    const hoje = new Date();

    const anoAtual = hoje.getFullYear();
    const mesAtual = hoje.getMonth();

    const nomesMeses = [
        "JANEIRO",
        "FEVEREIRO",
        "MARÇO",
        "ABRIL",
        "MAIO",
        "JUNHO",
        "JULHO",
        "AGOSTO",
        "SETEMBRO",
        "OUTUBRO",
        "NOVEMBRO",
        "DEZEMBRO"
    ];

    const tituloMes = document.querySelector("#mes_calendario");
    const calendario = document.querySelector("#calendario");

    if (!tituloMes || !calendario) {
        return;
    }

    tituloMes.textContent =
        `${nomesMeses[mesAtual]} ${anoAtual}`;

    const primeiroDia = new Date(
        anoAtual,
        mesAtual,
        1
    );

    const ultimoDia = new Date(
        anoAtual,
        mesAtual + 1,
        0
    );

    let primeiroDiaSemana = primeiroDia.getDay();

    if (primeiroDiaSemana === 0) {

        primeiroDiaSemana = 6;

    } else {

        primeiroDiaSemana--;

    }

    calendario.innerHTML = "";

    for (let i = 0; i < primeiroDiaSemana; i++) {

        const espaco = document.createElement("span");

        calendario.appendChild(espaco);

    }

    for (
        let diaNumero = 1;
        diaNumero <= ultimoDia.getDate();
        diaNumero++
    ) {

        const dia = document.createElement("span");

        const mesFormatado = String(
            mesAtual + 1
        ).padStart(2, "0");

        const diaFormatado = String(
            diaNumero
        ).padStart(2, "0");

        const data =
            `${anoAtual}-${mesFormatado}-${diaFormatado}`;

        dia.textContent = diaNumero;
        dia.dataset.date = data;

        calendario.appendChild(dia);

    }

    colorirDatas();

}

function colorirDatas() {

    /*
     * Dados temporários.
     *
     * Posteriormente poderão vir do backend.
     */

    const dias = document.querySelectorAll(
        "#calendario span[data-date]"
    );

    dias.forEach((dia) => {

        const data = dia.dataset.date;

        if (admissoes.includes(data)) {

            dia.classList.add("text-success");

        }

        if (desligamentos.includes(data)) {

            dia.classList.add("text-danger");

        }
    });
}