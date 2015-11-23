package br.com.tgolopes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.tgolopes.dto.CepDTO;
import br.com.tgolopes.entity.Endereco;
import br.com.tgolopes.exception.EnderecoNaoEncontradoException;
import br.com.tgolopes.service.EnderecoService;


/**
 * Classe que representa o serviço REST pela anotação @RestController que irá controlar a aplicação pelas chamadas do browser.
 * A anotação @RequestMapping mapea a url e a captura quando  mesma for digitada no browser.
 * 
 * @author Thiago Oliveira Lopes
 */
@RestController
@RequestMapping(value = "/endereco")
public class EnderecoController {

	//Atributo que representa a injeção de dependência 
	@Autowired
	private EnderecoService enderecoService;
	
	/**
	 * Método responsável por salvar um endereço recebendo um objeto Endereço e retornando um objeto JSON.
	 * @param endereco
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Endereco salvar(@RequestBody Endereco endereco) {
		return this.enderecoService.salvar(endereco);
	}
	
	/**
	 * Método responsável por alterar um endereço recebendo um objeto Endereço e retornando um objeto JSON.
	 * @param endereco
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Endereco alterar(@RequestBody Endereco endereco) {
		return this.enderecoService.salvar(endereco);
	}

	/**
	 * Método responsável por excluir um endereço recebendo o id do Endereço.
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void excluir(@PathVariable Long id) {
		this.enderecoService.deletar(id);
	}
	
	/**
	 * Método responsável por exibir um endereço passando o id do Endereço via GET, retornando um objeto do tipo ModelAndView.
	 * Caso o endereço não seja encontrado ele mostrará uma mensagem "Endereço não encontrado!".
	 * @param id
	 * @return
	 * @throws EnderecoNaoEncontradoException
	 */
	@RequestMapping(value = "/exibir/{id}", method = RequestMethod.GET)
	public ModelAndView exibirEndereco(@PathVariable Long id) throws EnderecoNaoEncontradoException {
		ModelAndView mView = new ModelAndView("detalharEndereco");
		Endereco endereco = this.pesquisarId(id);
		mView.addObject("endereco", endereco);
		return mView;
	}

	/**
	 * Método responsável por buscar um endereço pelo id do Endereço via GET, retornando um objeto JSON do Endereço.
	 * Caso o endereço não seja encontrado ele mostrará uma mensagem "Endereço não encontrado!".
	 * @param id
	 * @return
	 * @throws EnderecoNaoEncontradoException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Endereco pesquisarId(@PathVariable Long id) throws EnderecoNaoEncontradoException {
		Endereco endereco = this.enderecoService.pesquisarPorId(id);
		if (endereco == null)
			throw new EnderecoNaoEncontradoException();
		return endereco;
	}
	
	/**
	 * Método responsável por listar todos os endereços no formato JSON.
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Endereco> exibirTodos() {
		return enderecoService.pesquisarTodos();
	}
	
	/**
	 * Método responsável por efetuar uma pesquisa de Cep válido chamando o Serviço de busca da aplicação pesquisa-cep, retornando um objeto JSON do Cep.
	 * A aplicação pesquisa-cep deve estar no ar para ser feito a busca.
	 * @param cep
	 * @return
	 */
	@RequestMapping(value = "/chamadaServiceCEP/{cep}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public CepDTO pesquisarPorCep(@PathVariable String cep){
	    RestTemplate restTemplate = new RestTemplate();
	    String url="http://localhost:8081/pesquisarCep/{cep}";
	    CepDTO cepdto = restTemplate.getForObject(url, CepDTO.class,cep);
	    return cepdto;
	}
}

