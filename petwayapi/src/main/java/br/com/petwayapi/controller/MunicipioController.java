package br.com.petwayapi.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import br.com.petwayapi.models.Municipio;
import br.com.petwayapi.repository.MunicipioRepository;

@ApiOperation(value = "Retorna uma lista de municípios")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de municípios"),
        @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
        @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
})
@Controller
@RequestMapping("/municipio")
public class MunicipioController extends GenericController<Municipio, Integer> {
  
	@Autowired
    public MunicipioController(MunicipioRepository repo) {
        super(repo);
    }
}