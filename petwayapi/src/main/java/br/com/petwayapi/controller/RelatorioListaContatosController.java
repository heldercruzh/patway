package br.com.petwayapi.controller;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import br.com.petwayapi.payloader.Relatorio;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.*;
import org.springframework.http.HttpHeaders;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.*;
import org.springframework.util.ResourceUtils;

import br.com.petwayapi.models.Pessoa;
import br.com.petwayapi.repository.PessoaRepository;
import br.com.petwayapi.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
@RequestMapping("/lista_contatos")
public class RelatorioListaContatosController {

    @Autowired
    private ReportService service;

    @PostMapping
    public void gerarRelatorio(@RequestBody Relatorio relatorio, HttpServletResponse response) throws IOException, JRException {
        service.gerarRelatorio("lista_contatos", relatorio.getFormato(), relatorio.getArrayDados(), response);
    }
}