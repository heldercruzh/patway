package br.com.petwayapi.controller;

import br.com.petwayapi.models.Municipio;
import br.com.petwayapi.models.Uf;
import br.com.petwayapi.repository.UsuarioRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import br.com.petwayapi.models.Pessoa;
import br.com.petwayapi.repository.PessoaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@ApiOperation(value = "Retorna uma lista de pessoas")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de pessoas"),
        @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
        @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
})

@Controller
@RequestMapping("/pessoa")
public class PessoaController extends GenericController<Pessoa, Long> {

	@Autowired
    PessoaRepository repoPessoa;

    public PessoaController(PessoaRepository repo) {
	    super(repo);
    }

    @Autowired
    UsuarioRepository usuarioRepository;

    private Logger logger = LoggerFactory.getLogger(UsuarioController.class);


    @RequestMapping(value="/consulta", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody List<Pessoa> findByNome(
            @RequestParam Map<String, String> params) {

        return this.repoPessoa.consultaSpringData(
                params.get("cpf"),
                params.get("nome"),
                Integer.parseInt(params.get("uf")),
                Integer.parseInt(params.get("municipio"))
                );
    }

    /*
     * Override for update with encrypted password before to save
     * @author HelderSilvaCruz
     * @version 1.0
     */

    @Override
    public @ResponseBody Pessoa update (@PathVariable Long id, @RequestBody Pessoa json){

        this.logger.debug("update() of id#{} with body {}", id, json);
        this.logger.debug("T json is of type {}", json.getClass());

        Pessoa entity = super.repo.findById(id).get();

        if(!entity.getUsuario().getEmail().equals(json.getUsuario().getEmail())) {
            if (usuarioRepository.findByEmail(entity.getUsuario().getEmail()).size() < 0){
                //Email já existe
                return null;
            }
        }

        String encodedPassword;

        if(entity.getUsuario().getSenha().equals(json.getUsuario().getSenha())) {
            encodedPassword = entity.getUsuario().getSenha();
        } else {
            encodedPassword = new BCryptPasswordEncoder().encode(json.getUsuario().getSenha());
        }

        this.logger.debug("merged entity: {}", entity);

        json.getUsuario().setSenha(encodedPassword);

        Pessoa updated = super.repo.save(json);
        logger.debug("updated enitity: {}", updated);

        return updated;
    }

    /*
     * Override for create with encrypted password before to save
     * @author HelderSilvaCruz
     * @version 1.0
     */

    @Override
    public @ResponseBody Pessoa create(@RequestBody Pessoa json) {

        this.logger.debug("create() with body {} of type {}", json, json.getClass());

        if (usuarioRepository.existsByEmail(json.getUsuario().getEmail())){
            //Email já existe
            return null;
        }


        String encodedPassword = new BCryptPasswordEncoder().encode(json.getUsuario().getSenha());
        json.getUsuario().setSenha(encodedPassword);

        Pessoa created = this.repo.save(json);

        return created;
    }
}