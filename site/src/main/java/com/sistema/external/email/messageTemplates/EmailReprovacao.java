
package com.sistema.external.email.messageTemplates;

import com.sistema.external.email.EmailTemplate;

public class EmailReprovacao extends EmailTemplate {

    private final String nome;
    private final String empresa;

    public EmailReprovacao(String nome, String empresa) {
        this.nome = nome;
        this.empresa = empresa;
    }

    @Override
    protected String gerarConteudo() {
        return """
            <div style="
                padding: 40px 35px;
                text-align: center;
            ">

                <div style="
                    font-size: 52px;
                    margin-bottom: 15px;
                ">
                    &#128533;
                </div>

                <h2 style="
                    margin: 0 0 15px;
                    color: #dc3545;
                    font-size: 26px;
                ">
                    Solicitação não aprovada
                </h2>

                <p style="
                    font-size: 16px;
                    line-height: 1.6;
                    margin: 0 0 20px;
                ">
                    Olá, <strong>%s</strong>.
                </p>

                <p style="
                    font-size: 16px;
                    line-height: 1.6;
                    margin: 0 0 25px;
                ">
                    Informamos que a solicitação para cadastrar a
                    empresa <strong>%s</strong> no UnitHub
                    não foi aprovada.
                </p>

                <div style="
                    background-color: #fce8e8;
                    border-left: 5px solid #dc3545;
                    padding: 18px;
                    margin: 25px 0;
                    text-align: left;
                    border-radius: 6px;
                ">
                    <strong style="color: #dc3545;">
                        ✕ Solicitação não aprovada
                    </strong>

                    <p style="
                        margin: 8px 0 0;
                        color: #555555;
                        font-size: 14px;
                    ">
                        Para mais informações, entre em contato
                        com a equipe responsável.
                    </p>
                </div>

                <p style="
                    font-size: 15px;
                    line-height: 1.5;
                    color: #666666;
                    margin: 0;
                ">
                    Agradecemos pelo seu interesse no UnitHub.
                </p>

            </div>
            """.formatted(nome, empresa);
    }
}
