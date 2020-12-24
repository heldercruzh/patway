package br.com.petwayapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import br.com.petwayapi.models.Pessoa;
import br.com.petwayapi.repository.PessoaRepository;

@Controller
@RequestMapping("/pessoa")
public class PessoaController extends GenericController<Pessoa, Long> {
  
	@Autowired
    public PessoaController(PessoaRepository repo) {
        super(repo);
    }
}