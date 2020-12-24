package br.com.petwayapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import br.com.petwayapi.models.Municipio;
import br.com.petwayapi.repository.MunicipioRepository;

@Controller
@RequestMapping("/municipio")
public class MunicipioController extends GenericController<Municipio, Integer> {
  
	@Autowired
    public MunicipioController(MunicipioRepository repo) {
        super(repo);
    }
}