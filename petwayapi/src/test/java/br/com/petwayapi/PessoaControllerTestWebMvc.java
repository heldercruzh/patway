package br.com.petwayapi;

import br.com.petwayapi.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PessoaControllerTestWebMvc {

    @Autowired
    private MockMvc mvc;

    @Test
    public void listAll() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders
                .get("/pessoa")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty());

    }

    @Test
    public void findbById() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .get("/pessoa/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    public void create() throws Exception
    {

        Pessoa pessoa = new Pessoa();
        pessoa.setId(null);
        pessoa.setNome("Nome Teste");

        Usuario usuario = new Usuario();
        usuario.setEmail("h@g.c");
        usuario.setSenha("123");
        Perfil perfil = new Perfil();
        perfil.setId(1);
        usuario.setPerfil(perfil);

        pessoa.setUsuario(usuario);
        pessoa.setCpf("00000000000");
        pessoa.setRg("0000000");

        Uf ssp = new Uf();
        ssp.setId(1);

        pessoa.setSsp(ssp);
        pessoa.setDataNascimento("2000-12-01");
        pessoa.setGenero(true);
        pessoa.setTelefone("00000000000");
        pessoa.setCelular("00000000000");
        pessoa.setCep("00000000");

        Municipio municipio = new Municipio();
        municipio.setId(1);

        pessoa.setMunicipio(municipio);
        pessoa.setBairro("Bairro Teste");
        pessoa.setEndereco("Endereço Teste");
        pessoa.setNumero("01");
        pessoa.setComplemento("Complemento Teste");
        pessoa.setAtivo(true);

        mvc.perform( MockMvcRequestBuilders
                .post("/pessoa")
                .content(asJsonString(pessoa))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void updatePessoaAPI() throws Exception
    {

        Pessoa pessoa = new Pessoa();
        pessoa.setId(new Long(1));
        pessoa.setNome("Nome Teste");

        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setEmail("h@g.c");
        usuario.setSenha("123");
        Perfil perfil = new Perfil();
        perfil.setId(1);
        usuario.setPerfil(perfil);

        pessoa.setUsuario(usuario);
        pessoa.setCpf("00000000000");
        pessoa.setRg("0000000");

        Uf ssp = new Uf();
        ssp.setId(1);

        pessoa.setSsp(ssp);
        pessoa.setDataNascimento("2000-12-01");
        pessoa.setGenero(true);
        pessoa.setTelefone("00000000000");
        pessoa.setCelular("00000000000");
        pessoa.setCep("00000000");

        Municipio municipio = new Municipio();
        municipio.setId(1);

        pessoa.setMunicipio(municipio);
        pessoa.setBairro("Bairro Teste");
        pessoa.setEndereco("Endereço Teste");
        pessoa.setNumero("01");
        pessoa.setComplemento("Complemento Teste");
        pessoa.setAtivo(true);

        mvc.perform( MockMvcRequestBuilders
                .put("/pessoa/{id}", 1)
                .content(asJsonString(pessoa))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void remove() throws Exception
    {
        /*mvc.perform( MockMvcRequestBuilders.delete("/pessoa/{id}", 1) )
                .andExpect(status().isOk());*/
    }
}
