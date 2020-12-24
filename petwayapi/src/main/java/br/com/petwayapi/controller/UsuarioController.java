package br.com.petwayapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import br.com.petwayapi.models.Usuario;
import br.com.petwayapi.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuario")
public class UsuarioController extends GenericController<Usuario, Integer> {
  
	@Autowired
    public UsuarioController(UsuarioRepository repo) {
        super(repo);
    }
}