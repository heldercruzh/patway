package br.com.petwayapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.petwayapi.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
