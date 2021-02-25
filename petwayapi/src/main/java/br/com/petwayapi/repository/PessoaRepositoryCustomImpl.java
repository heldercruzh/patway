package br.com.petwayapi.repository;

import br.com.petwayapi.models.Municipio;
import br.com.petwayapi.models.Pessoa;
import br.com.petwayapi.models.Uf;
import br.com.petwayapi.models.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PessoaRepositoryCustomImpl implements PessoaRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Pessoa> consultaHql(String cpf, String nome, Integer uf, Integer municipio) {
        StringBuilder sb = new StringBuilder();
        sb.append("FROM Pessoa AS tb1 INNER JOIN Municipio AS tb2 ON tb1.municipio = tb2.id WHERE 0 = 0 ");

        if(!cpf.isEmpty()) {
            sb.append(" AND tb1.cpf = :cpf ");
        }
        if(!nome.isEmpty()) {
            sb.append(" AND tb1.nome = :nome ");
        }
        if(uf != 0) {
            sb.append(" AND tb2.uf.id = :uf ");
        }
        if(municipio != 0) {
            sb.append(" AND tb1.municipio.id = :municipio ");
        }

        Query query = entityManager.createQuery(sb.toString());

        if(!cpf.isEmpty()) {
            query.setParameter("cpf", cpf);
        }
        if(!nome.isEmpty()) {
            query.setParameter("nome", nome);
        }
        if(uf != 0) {
            query.setParameter("uf", uf);
        }
        if(municipio != 0) {
            query.setParameter("municipio", municipio);
        }

        return query.getResultList();
    }


    @Override
    public List<Pessoa> consultaNative(String cpf, String nome, Integer uf, Integer municipio) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT tb1.* FROM pessoa AS tb1 INNER JOIN municipio AS tb2 ON tb1.municipio_id = tb2.id WHERE 0 = 0 ");

        if(!cpf.isEmpty()) {
            sb.append(" AND tb1.cpf = :cpf ");
        }
        if(!nome.isEmpty()) {
            sb.append(" AND tb1.nome = :nome ");
        }
        if(uf != 0) {
            sb.append(" AND tb2.uf_id = :uf ");
        }
        if(municipio != 0) {
            sb.append(" AND tb1.municipio_id = :municipio ");
        }

        System.out.println(sb.toString());
        Query query = entityManager.createNativeQuery(sb.toString());

        if(!cpf.isEmpty()) {
            query.setParameter("cpf", cpf);
        }
        if(!nome.isEmpty()) {
            query.setParameter("nome", nome);
        }
        if(uf != 0) {
            query.setParameter("uf", uf);
        }
        if(municipio != 0) {
            query.setParameter("municipio", municipio);
        }

        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        List<Object[]> objs = query.getResultList();

        for (Object[] o : objs) {

            Object[] aux = o;
            Pessoa pessoa = new Pessoa();

            //0 - id
            pessoa.setId(((BigInteger) aux[0]).longValue());
            //1 - ativo
            pessoa.setAtivo((boolean) aux[1]);
            //2 - bairro
            pessoa.setBairro((String) aux[2]);
            //3 - celular
            pessoa.setCelular((String) aux[3]);
            //4 - cep
            pessoa.setCep((String) aux[4]);
            //5 - complemento
            pessoa.setComplemento((String) aux[5]);
            //6 - cpf
            pessoa.setCpf((String) aux[6]);
            //7 - data_nascimento
            pessoa.setDataNascimento((String) aux[7]);
            //8 - endereco
            pessoa.setEndereco((String) aux[8]);
            //9 - genero
            pessoa.setGenero((boolean) aux[9]);
            //10 - nome
            pessoa.setNome((String) aux[10]);
            //11 - numero
            pessoa.setNumero((String) aux[11]);
            //12 - rg
            pessoa.setRg((String) aux[12]);
            //13 - telefone
            pessoa.setTelefone((String) aux[13]);
            //14 - municipio_id
            Municipio municipioObj = new Municipio();
            municipioObj.setId((Integer) aux[14]);
            pessoa.setMunicipio(municipioObj);
            //15 - ssp_id
            Uf ufObj = new Uf();
            ufObj.setId((Integer) aux[15]);
            pessoa.setSsp(ufObj);
            //16 - usuario_id
            Query uQuery = entityManager.createQuery("FROM Usuario AS u WHERE u.id = :usuarioId");
            uQuery.setParameter("usuarioId",(Integer) aux[16] );
            pessoa.setUsuario((Usuario) uQuery.getSingleResult());

            pessoas.add(pessoa);
        }

        return pessoas;

    }

}
