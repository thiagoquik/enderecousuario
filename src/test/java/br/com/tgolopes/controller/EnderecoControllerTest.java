package br.com.tgolopes.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import br.com.tgolopes.EnderecoUsuarioApplication;
import br.com.tgolopes.entity.Endereco;
import br.com.tgolopes.help.HelpTest;

import com.google.gson.Gson;

/**
 * 
 * @author Thiago Oliveira Lopes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EnderecoUsuarioApplication.class)
@WebAppConfiguration
public class EnderecoControllerTest {

    private MockMvc mockMvc;
    private static Gson gson = new Gson();

    @Autowired
    private WebApplicationContext context;
    
    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(this.context).build();
    }
    
    @Test
    public void criacaoNovoEnderecoValido() throws Exception{
    	Endereco end = new Endereco();
    	end.setNumCep("12345678");
    	end.setLogradouro("Rua Aranha");
    	end.setBairro("Vila Batistini");
    	end.setCidade("São Bernardo do Campo");
    	end.setEstado("São Paulo");
    	end.setNumEndereco("158");
    	end.setComplemento("AP 15");
        this.mockMvc.perform(post("/endereco")
				.contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(end))
                .accept(MediaType.APPLICATION_JSON))
				.andDo(HelpTest.setContentType("charset=utf-8"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void alterarEnderecoValido() throws Exception{
    	Endereco end = new Endereco();
    	end.setNumCep("12345678");
    	end.setLogradouro("Rua Aranha");
    	end.setBairro("Vila Batistini");
    	end.setCidade("São Bernardo do Campo");
    	end.setEstado("São Paulo");
    	end.setNumEndereco("777");
    	end.setComplemento("AP 15");
        this.mockMvc.perform(put("/endereco")
				.contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(end))
                .accept(MediaType.APPLICATION_JSON))
				.andDo(HelpTest.setContentType("charset=utf-8"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void alterarEnderecoNaoValido() throws Exception{
    	Endereco end = new Endereco();
    	end.setNumCep("8787aed4");
    	end.setLogradouro("Rua Aranha");
    	end.setBairro("Vila Batistini");
    	end.setCidade("São Bernardo do Campo");
    	end.setEstado("São Paulo");
    	end.setNumEndereco("158");
    	end.setComplemento("AP 15");
        this.mockMvc.perform(put("/endereco")
				.contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(end))
                .accept(MediaType.APPLICATION_JSON))
				.andDo(HelpTest.setContentType("charset=utf-8"))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void validaEnderecoJaCadastrado() throws Exception{
    	Long idEndereco = 2l;
    	this.mockMvc.perform(get(String.format("/endereco/%s", idEndereco))
    			.accept(MediaType.APPLICATION_JSON))
    			.andDo(HelpTest.setContentType("charset=utf-8"))
    			.andExpect(jsonPath("logradouro", equalTo("Rua Bororé")))
    			.andExpect(jsonPath("numEndereco", equalTo("10")))
    			.andExpect(jsonPath("complemento", equalTo("ap - 15")))
    			.andExpect(jsonPath("cidade", equalTo("Santo André")))
    			.andExpect(jsonPath("estado", equalTo("São Paulo")))
    			.andExpect(jsonPath("numCep", equalTo("09178523")))
    			.andExpect(status().isOk());
    }    
    
    @Test
    public void validaEnderecoNaoCadastrado() throws Exception{
    	Long idEndereco = 27l;
    	this.mockMvc.perform(get(String.format("/endereco/%s", idEndereco))
    			.accept(MediaType.APPLICATION_JSON))
    			.andDo(HelpTest.setContentType("charset=utf-8"))
    			.andExpect(status().isNotFound());
    } 
    
    @Test
    public void criacaoNovoEnderecoSemCep() throws Exception{
    	Endereco end = new Endereco();
    	end.setLogradouro("Rua Aranha");
    	end.setBairro("Vila Batistini");
    	end.setCidade("São Bernardo do Campo");
    	end.setEstado("São Paulo");
    	end.setNumEndereco("158");
    	end.setComplemento("AP 15");
        this.mockMvc.perform(post("/endereco")
				.contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(end))
                .accept(MediaType.APPLICATION_JSON))
				.andDo(HelpTest.setContentType("charset=utf-8"))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void criacaoNovoEnderecoSemLogradouro() throws Exception{
    	Endereco end = new Endereco();
    	end.setNumCep("12345678");
    	end.setBairro("Vila Batistini");
    	end.setCidade("São Bernardo do Campo");
    	end.setEstado("São Paulo");
    	end.setNumEndereco("158");
    	end.setComplemento("AP 15");
        this.mockMvc.perform(post("/endereco")
				.contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(end))
                .accept(MediaType.APPLICATION_JSON))
				.andDo(HelpTest.setContentType("charset=utf-8"))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void criacaoNovoEnderecoSemBairro() throws Exception{
    	Endereco end = new Endereco();
    	end.setNumCep("12345678");
    	end.setLogradouro("Rua Aranha");
    	end.setCidade("São Bernardo do Campo");
    	end.setEstado("São Paulo");
    	end.setNumEndereco("158");
    	end.setComplemento("AP 15");    			
    	this.mockMvc.perform(post("/endereco").contentType(MediaType.APPLICATION_JSON)
	        .content(gson.toJson(end))
	        .accept(MediaType.APPLICATION_JSON))
	        .andDo(HelpTest.setContentType("charset=utf-8"))
	        .andExpect(status().isOk())
	        .andDo(print()).andReturn();
    }
    
    @Test
    public void criacaoNovoEnderecoSemCidade() throws Exception{
    	Endereco end = new Endereco();
    	end.setNumCep("12345678");
    	end.setLogradouro("Rua Aranha");
    	end.setBairro("Vila Batistini");
    	end.setEstado("São Paulo");
    	end.setNumEndereco("158");
    	end.setComplemento("AP 15");
        this.mockMvc.perform(post("/endereco")
				.contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(end))
                .accept(MediaType.APPLICATION_JSON))
				.andDo(HelpTest.setContentType("charset=utf-8"))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void criacaoNovoEnderecoSemEstado() throws Exception{
    	Endereco end = new Endereco();
    	end.setNumCep("12345678");
    	end.setLogradouro("Rua Aranha");
    	end.setBairro("Vila Batistini");
    	end.setCidade("São Bernardo do Campo");
    	end.setNumEndereco("158");
    	end.setComplemento("AP 15");
        this.mockMvc.perform(post("/endereco")
				.contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(end))
                .accept(MediaType.APPLICATION_JSON))
				.andDo(HelpTest.setContentType("charset=utf-8"))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void criacaoNovoEnderecoSemNumero() throws Exception{
    	Endereco end = new Endereco();
    	end.setNumCep("12345678");
    	end.setLogradouro("Rua Aranha");
    	end.setBairro("Vila Batistini");
    	end.setCidade("São Bernardo do Campo");
    	end.setEstado("São Paulo");
    	end.setComplemento("AP 15");
        this.mockMvc.perform(post("/endereco")
				.contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(end))
                .accept(MediaType.APPLICATION_JSON))
				.andDo(HelpTest.setContentType("charset=utf-8"))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void criacaoNovoEnderecoSemComplemento() throws Exception{
    	Endereco end = new Endereco();
    	end.setNumCep("12345678");
    	end.setLogradouro("Rua Aranha");
    	end.setBairro("Vila Batistini");
    	end.setCidade("São Bernardo do Campo");
    	end.setEstado("São Paulo");
    	end.setNumEndereco("158");
        this.mockMvc.perform(post("/endereco")
				.contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(end))
                .accept(MediaType.APPLICATION_JSON))
				.andDo(HelpTest.setContentType("charset=utf-8"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void deletarEnderecoCadastrado() throws Exception{
    	 Long idEndereco = 1l;
         this.mockMvc.perform(delete(String.format("/endereco/%s", idEndereco))
                         .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                         .andDo(HelpTest.setContentType("charset=utf-8"))
                         .andExpect(status().isOk());
    }
    
    @Test
    public void deletarEnderecoNaoCadastrado() throws Exception{
    	 Long idEndereco = 27l;
         this.mockMvc.perform(delete(String.format("/endereco/%s", idEndereco))
                         .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                         .andDo(HelpTest.setContentType("charset=utf-8"))
                         .andExpect(status().isBadGateway());
    }
    
    
    @Test
    public void criacaoNovoEnderecoCepInvalido() throws Exception{
    	Endereco endereco = new Endereco();
    	endereco.setLogradouro("Rua São Judas Tadeu");
    	endereco.setNumEndereco("612F");
    	endereco.setBairro("Vila Marcelo");
    	endereco.setCidade("São Paulo");
    	endereco.setEstado("São Paulo");
    	endereco.setNumCep("58965sew");
    	
    	this.mockMvc.perform(post("/endereco").contentType(MediaType.APPLICATION_JSON)
    	        .content(gson.toJson(endereco))
    	        .accept(MediaType.APPLICATION_JSON))
    	        .andDo(HelpTest.setContentType("charset=utf-8"))
    	        .andExpect(status().isBadRequest())
    	        .andDo(print());
    }
}
