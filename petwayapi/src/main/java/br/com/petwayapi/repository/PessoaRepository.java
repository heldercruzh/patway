package br.com.petwayapi.repository;

import br.com.petwayapi.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.petwayapi.models.Pessoa;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaRepositoryCustom{

    @Query(value="SELECT * FROM pessoa tb1 INNER JOIN municipio tb2 ON tb1.municipio_id = tb2.id "+
            "INNER JOIN uf tb3 ON tb2.uf_id = tb3.id WHERE 0 = 0 "+
            "AND (:cpf = '' OR tb1.cpf = :cpf) "+
            "AND (:nome = '' OR tb1.nome = :nome) "+
            "AND (:uf = 0 OR tb3.id = :uf) "+
            "AND (:municipio = 0 OR tb2.id = :municipio) "
            , nativeQuery = true)
    List<Pessoa> consultaSpringData(
            @Param("cpf") String cpf,
            @Param("nome") String nome,
            @Param("uf") Integer uf,
            @Param("municipio") Integer municipio
    );

}
