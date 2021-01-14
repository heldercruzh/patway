package br.com.petwayapi.repository;
import br.com.petwayapi.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

    Boolean existsByEmail(String email);
    List<Usuario> findByEmail(String email);

}
