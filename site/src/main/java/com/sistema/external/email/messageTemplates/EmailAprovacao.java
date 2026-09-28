package com.sistema.external.email.messageTemplates;

import com.sistema.external.email.EmailTemplate;

public class EmailAprovacao extends EmailTemplate {

    private final String nome;
    private final String empresa;

    public EmailAprovacao(String nome, String empresa) {
        this.nome = nome;
        this.empresa = empresa;
    }

	@Override
	protected String gerarConteudo() {
		return """
                <div style="
                    padding: 5px 3px;
                    text-align: center;
                ">

                    <div style="
                        font-size: 52px;
                        margin-bottom: 8px;
                    ">
                        &#127881;
                    </div>

                    <h2 style="
                        margin: 0 0 15px;
                        color: #198754;
                        font-size: 26px;
                    ">
                        Solicitação aprovada!
                    </h2>

                    <p style="
                        font-size: 16px;
                        line-height: 1.6;
                        margin: 0 0 20px;
                    ">
                        Olá, <strong>%s</strong>!
                    </p>

                    <p style="
                        font-size: 16px;
                        line-height: 1.6;
                        margin: 0 0 25px;
                    ">
                        Temos uma ótima notícia! Sua solicitação para
                        cadastrar a empresa <strong>%s</strong> no UnitHub
                        foi analisada e <strong>aprovada</strong>.
                    </p>

                    <div style="
                        background-color: #e8f5e9;
                        border-left: 5px solid #198754;
                        padding: 18px;
                        margin: 25px 0;
                        text-align: left;
                        border-radius: 6px;
                    ">
                        <strong style="color: #198754;">
                            ✓ Cadastro aprovado
                        </strong>

                        <p style="
                            margin: 8px 0 0;
                            color: #555555;
                            font-size: 14px;
                        ">
                            Sua empresa já pode utilizar os recursos
                            disponibilizados pelo UnitHub.
                        </p>
                    </div>

                    <p style="
                        font-size: 15px;
                        line-height: 1.5;
                        color: #666666;
                        margin-bottom: 30px;
                    ">
                        Seja bem-vindo ao UnitHub! &#128640;
                    </p>

                    <a 
                    style="
                        display: inline-block;
                        padding: 14px 30px;
                        background-color: #198754;
                        color: #ffffff;
                        text-decoration: none;
                        border-radius: 6px;
                        font-weight: bold;
                        font-size: 15px;
                    ">
                        Acessar o UnitHub
                    </a>

                </div>
                """.formatted(nome, empresa);
	}

    
}

