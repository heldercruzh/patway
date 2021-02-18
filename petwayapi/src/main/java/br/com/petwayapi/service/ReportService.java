package br.com.petwayapi.service;

import br.com.petwayapi.payloader.Relatorio;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.export.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    public void gerarRelatorio (String nomeRelatorio, String formato, List<Object> dados, HttpServletResponse response) throws IOException, JRException {

        //Definir o local de armazenamento do relatório gerado
        File file = ResourceUtils.getFile("relatorios");
        String path = file.getAbsolutePath();

        //Compilar e salvar Layout
        JasperReport jasperReport = JasperCompileManager.compileReport(path+"/"+nomeRelatorio+".jrxml");
        JRSaver.saveObject(jasperReport, path+"/"+nomeRelatorio+".jasper");

        //Define o valor dos parametros
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("nomeEmpresa", "Petway");
        parameters.put("cabecalho", "Brasília-DF, 411 Norte Bl A Sl 411");
        parameters.put("assinatura", "Helder Silva Cruz");
        parameters.put("img", path + "/spring-by-pivotal.png");

        //Definir os dados do corpo
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dados);

        // Passa para o JasperPrint o relatório, os parâmetros e a fonte dos dados
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        //Gerar o arquivo no formato definido
        switch(formato.toLowerCase()) {
            case "pdf": //OK
                JRPdfExporter exporterPdf = new JRPdfExporter();
                SimplePdfReportConfiguration reportConfigPdf = new SimplePdfReportConfiguration();
                exporterPdf.setConfiguration(reportConfigPdf);
                exporterPdf.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporterPdf.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
                exporterPdf.exportReport();
                break;
            case "html"://OK
                HtmlExporter exporterHtml = new HtmlExporter();
                exporterHtml.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporterHtml.setExporterOutput(new SimpleHtmlExporterOutput(response.getOutputStream()));
                exporterHtml.exportReport();
                //<<Deprecated>>
                //JRExporter exporter = new JRXhtmlExporter();
                //exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                //exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
                //exporter.exportReport();
                break;
            case "csv": //OK
                JRCsvExporter exporterCsv = new JRCsvExporter();
                exporterCsv.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporterCsv.setExporterOutput(new SimpleWriterExporterOutput(response.getOutputStream()));
                exporterCsv.exportReport();
                break;
            case "xlsx": //OK
                JRXlsxExporter exporterXlsx = new JRXlsxExporter();

                SimpleXlsxReportConfiguration reportConfigXlsx = new SimpleXlsxReportConfiguration();
                reportConfigXlsx.setOnePagePerSheet(false);
                reportConfigXlsx.setRemoveEmptySpaceBetweenRows(true);
                reportConfigXlsx.setDetectCellType(false);
                reportConfigXlsx.setWhitePageBackground(false);
                exporterXlsx.setConfiguration(reportConfigXlsx);

                exporterXlsx.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporterXlsx.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
                exporterXlsx.exportReport();
                break;
            default:
                System.out.println("formato de relatório não identificado");
        }

        // Monta a resposta no header
        response.setContentType("application/"+formato);
        response.addHeader("Content-disposition", "attachment; filename="+nomeRelatorio+"."+formato);
        final OutputStream outStream = response.getOutputStream();

    }




}
