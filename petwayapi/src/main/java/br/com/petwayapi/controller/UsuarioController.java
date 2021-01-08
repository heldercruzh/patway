package br.com.petwayapi.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import br.com.petwayapi.models.Usuario;
import br.com.petwayapi.repository.UsuarioRepository;


@ApiOperation(value = "Retorna uma lista de ueuários")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de usuários"),
        @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
        @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
})
@Controller
@RequestMapping("/usuario")
public class UsuarioController extends GenericController<Usuario, Integer> {

    @Autowired
    public UsuarioController(UsuarioRepository repo) {
        super(repo);
    }

}
