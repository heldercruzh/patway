package br.com.petwayapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.petwayapi.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{


}
