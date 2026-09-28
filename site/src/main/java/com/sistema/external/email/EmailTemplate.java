package com.sistema.external.email;

public abstract class EmailTemplate {

    public final String gerarEmail() {
        return abrirDocumento()
                + gerarCabecalho()
                + gerarConteudo()
                + gerarRodape()
                + fecharDocumento();
    }

    protected String abrirDocumento() {
        return """
            <!DOCTYPE html>
            <html lang="pt-BR">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                </head>

                <body style="
                    margin: 0;
                    padding: 0;
                    background-color: #f4f6f8;
                    font-family: Arial, Helvetica, sans-serif;
                    color: #333333;
                ">

                    <div style="
                        max-width: 600px;
                        margin: 5px auto;
                        background-color: #ffffff;
                        border-radius: 12px;
                        overflow: hidden;
                        box-shadow: 0 4px 15px rgba(0,0,0,0.08);
                    ">
            """;
    }

    protected String gerarCabecalho() {
        return """
                <div style="
                    background-color: #212529;
                    padding: 5px;
                    text-align: center;
                ">
                    <h1 style="
                        margin: 0;
                        color: #ffffff;
                        font-size: 28px;
                        letter-spacing: 1px;
                    ">
                        UNIT HUB
                    </h1>

                    <p style="
                        margin: 2px 0 0;
                        color: #ced4da;
                        font-size: 14px;
                    ">
                        Gestão empresarial simplificada
                    </p>
                </div>
            """;
    }

    
    protected String gerarRodape() {
        return """
                <div style="
                    border-top: 1px solid #eeeeee;
                    padding: 3px;
                    text-align: center;
                    color: #888888;
                    font-size: 12px;
                ">
                    <p style="margin: 0;">
                        Este é um e-mail automático do UnitHub.
                    </p>

                    <p style="margin: 6px 0 0;">
                        © 2026 UnitHub — Todos os direitos reservados.
                    </p>
                </div>
            """;
    }

    protected String fecharDocumento() {
        return """
                </div>
                </body>
            </html>
            """;
    }
    
    
    protected abstract String gerarConteudo();
}
