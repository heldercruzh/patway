package br.com.petwayapi.controller;

import javax.servlet.http.HttpServletResponse;

import br.com.petwayapi.payload.RelatorioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import br.com.petwayapi.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import java.io.IOException;

@Controller
@RequestMapping("/lista_contatos")
public class RelatorioListaContatosController {

    @Autowired
    private ReportService service;

    @PostMapping
    public void gerarRelatorio(@RequestBody RelatorioRequest relatorio, HttpServletResponse response) throws IOException, JRException {
        service.gerarRelatorio("lista_contatos", relatorio.getFormato(), relatorio.getArrayDados(), response);
    }
}