package br.com.petwayapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import br.com.petwayapi.models.Uf;
import br.com.petwayapi.repository.UfRepository;

@Controller
@RequestMapping("/uf")
public class UfController extends GenericController<Uf, Integer> {
  
	@Autowired
    public UfController(UfRepository repo) {
        super(repo);
        
        
    }
}