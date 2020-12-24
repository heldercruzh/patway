package br.com.petwayapi.controller;

import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/*
 * Classe genérica para facilitar a implementação de classes RestController
 * 
 * Modo de usar: Criar uma classe controller, extender esta classe e passar o respectivo objeto repository que se deseja consultar.
 * 
 */


public abstract class GenericController<T, ID extends Serializable> {


    private Logger logger = LoggerFactory.getLogger(GenericController.class);

    protected JpaRepository<T, ID> repo;


    public GenericController(JpaRepository<T, ID> repo) {
        this.repo = repo;
    }

    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody List<T> listAll() {
    	Iterable<T> all = this.repo.findAll();
        return Lists.newArrayList(all);
        
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public @ResponseBody T get(@PathVariable ID id) {
        return this.repo.findById(id).get();
    }
    

    @RequestMapping(method=RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody T create(@RequestBody T json) {
    	logger.debug("create() with body {} of type {}", json, json.getClass());

        T created = this.repo.save(json);

        return created;
    }

    
    
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public @ResponseBody T delete(@PathVariable ID id) {
        
    	T entity = this.repo.findById(id).get();
    	
    	this.repo.deleteById(id);
                
        logger.debug("deleted enitity by id: id#{}", id);
                
        return entity;
          
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public @ResponseBody T update(@PathVariable ID id, @RequestBody T json) {
    	
    	logger.debug("update() of id#{} with body {}", id, json);
        logger.debug("T json is of type {}", json.getClass());
    	
    	T entity = this.repo.findById(id).get();
        try {
            BeanUtils.copyProperties(entity, json);
        }
        catch (Exception e) {
            logger.warn("while copying properties", e);
            throw Throwables.propagate(e);
        }

        logger.debug("merged entity: {}", entity);

        T updated = this.repo.save(entity);
        
        logger.debug("updated enitity: {}", updated);

        
        return updated;
    }
    
       
}