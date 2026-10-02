package com.sistema.generation;

import java.io.ByteArrayOutputStream;

import org.openpdf.text.Document;
import org.openpdf.text.pdf.PdfWriter;

public abstract class RelatorioPdfTemplate {

    public final byte[] gerar() {

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Document document = new Document();

        try {

            PdfWriter.getInstance(document, output);

            document.open();
            adicionarCabecalho(document);
            adicionarResumo(document);
            adicionarGraficos(document);
            adicionarTabelas(document);
            adicionarRodape(document);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            document.close();
        }

        return output.toByteArray();
    }

    protected abstract void adicionarCabecalho(Document document) throws Exception;
    protected abstract void adicionarResumo(Document document) throws Exception;
    protected abstract void adicionarGraficos(Document document) throws Exception;
    protected abstract void adicionarTabelas(Document document) throws Exception;
    protected abstract void adicionarRodape(Document document) throws Exception;
}