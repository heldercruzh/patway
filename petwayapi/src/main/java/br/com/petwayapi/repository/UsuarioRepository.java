package br.com.petwayapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.petwayapi.models.Usuario;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

    Boolean existsByEmail(String email);
    List<Usuario> findByEmail(String email);

}
