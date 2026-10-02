package com.sistema.generation.relatorioTemplates;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.CategorySeries;
import org.openpdf.text.Document;
import org.openpdf.text.Image;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.PdfPTable;

import com.sistema.models.Funcionario;
import com.sistema.generation.RelatorioPdfTemplate;

public class RelatorioRH extends RelatorioPdfTemplate {

    private List<Funcionario> funcionarios;

    public RelatorioRH(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    @Override
    protected void adicionarCabecalho(Document document) throws Exception {
        document.add(new Paragraph("Relatório de Recursos Humanos"));
        document.add(new Paragraph("Data de geração: " + java.time.LocalDate.now()));
        document.add(new Paragraph(" "));
    }

    @Override
    protected void adicionarResumo(Document document) throws Exception {
        int totalFuncionarios = funcionarios.size();

        double folhaSalarial = funcionarios.stream().mapToDouble(f -> Double.parseDouble(f.getSalario())).sum();

        document.add(new Paragraph("Resumo"));
        document.add(new Paragraph("Total de funcionários: " + totalFuncionarios));
        document.add(new Paragraph("Folha salarial total: R$ " + folhaSalarial));
        document.add(new Paragraph(" "));
    }

    @Override
    protected void adicionarGraficos(Document document) throws Exception {
        adicionarGraficoFuncionariosPorDepartamento(document);
        document.add(new Paragraph(" "));
        adicionarGraficoAdmissoesPorMes(document);
    }

    @Override
    protected void adicionarTabelas(Document document) throws Exception {
        document.add(new Paragraph("Funcionários:"));
        document.add(new Paragraph(" "));

        PdfPTable tabela = new PdfPTable(5);

        tabela.addCell("Nome");
        tabela.addCell("Cargo");
        tabela.addCell("Departamento");
        tabela.addCell("Admissão");
        tabela.addCell("Estado");

        for (Funcionario funcionario : funcionarios) {
            tabela.addCell(funcionario.getNome());
            tabela.addCell(funcionario.getCargo());
            tabela.addCell(funcionario.getNomeDepartamento());
            tabela.addCell(funcionario.getData_admissao());
            tabela.addCell(funcionario.getEstado());
        }

        document.add(tabela);
    }

    @Override
    protected void adicionarRodape(Document document) throws Exception {
        document.add(new Paragraph("Relatório gerado pelo UnitHub"));
    }

    //MÉTODOS PRIVADOS:
    private void adicionarGraficoFuncionariosPorDepartamento(Document document) throws Exception {
        Map<String, Integer> dados = new LinkedHashMap<>();
        for (Funcionario f : funcionarios) {
            String departamento = f.getNomeDepartamento();
            if (departamento == null || departamento.isBlank())
                departamento = "Sem departamento";
            dados.put(departamento, dados.getOrDefault(departamento, 0) + 1);
        }

        CategoryChart chart = new CategoryChartBuilder().width(700).height(400).title("Funcionários por departamento").xAxisTitle("Departamento").yAxisTitle("Funcionários").build();
        chart.addSeries("Funcionários", new ArrayList<>(dados.keySet()), new ArrayList<>(dados.values()));

        byte[] imagem = BitmapEncoder.getBitmapBytes(chart, BitmapEncoder.BitmapFormat.PNG);

        Image imagemPdf = Image.getInstance(imagem);
        imagemPdf.scaleToFit(500, 300);
        
        document.add(new Paragraph("Funcionários por departamento"));
        document.add(imagemPdf);
    }

    private void adicionarGraficoAdmissoesPorMes(Document document) throws Exception {
        Map<String, Integer> dados = new LinkedHashMap<>();
        for (Funcionario f : funcionarios) {
            String data = f.getData_admissao();
            if (data == null || data.length() < 7)
                continue;
            String mes = data.substring(0, 7);
            dados.put(mes, dados.getOrDefault(mes, 0) + 1);
        }

        CategoryChart chart = new CategoryChartBuilder().width(700).height(400).title("Admissões por mês").xAxisTitle("Mês").yAxisTitle("Admissões").build();
        chart.getStyler().setDefaultSeriesRenderStyle(CategorySeries.CategorySeriesRenderStyle.Line);
        chart.addSeries("Admissões", new ArrayList<>(dados.keySet()), new ArrayList<>(dados.values()));

        byte[] imagem = BitmapEncoder.getBitmapBytes(chart, BitmapEncoder.BitmapFormat.PNG);

        Image imagemPdf = Image.getInstance(imagem);
        imagemPdf.scaleToFit(500, 300);
        
        document.add(new Paragraph("Admissões por mês"));
        document.add(imagemPdf);
    }

}