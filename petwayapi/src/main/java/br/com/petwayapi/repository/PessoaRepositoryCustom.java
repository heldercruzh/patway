package br.com.petwayapi.repository;

import br.com.petwayapi.models.Pessoa;

import java.util.List;

public interface PessoaRepositoryCustom {

    public List<Pessoa> consultaHql(String cpf, String nome, Integer uf, Integer municipio);

    public List<Pessoa> consultaNative(String cpf, String nome, Integer uf, Integer municipio);

}
