package br.com.tgolopes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.com.tgolopes.entity.Endereco;
import br.com.tgolopes.repository.EnderecoRepository;

/**
 * Classe que representa os serviços de administração de Endereço que será consumido pela classe EnderecoController
 * 
 * @author Thiago Oliveira Lopes
 */
@Service
@Validated
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class EnderecoService {
	
	//Atributo que representa a injeção de dependência
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	/**
	 * Método responsável por salvar um Endereço.
	 * Retorna o Endereço salvo.
	 * A anotação @Transactional define que este método terá abrir uma transação com o banco de dadosm e caso haja alguem erro ele se encarrega de fazer o Rollback. 
	 * @param endereco
	 * @return
	 */
	@Transactional
	public Endereco salvar(final Endereco endereco){
		return this.enderecoRepository.save(endereco);
	}
	
	/**
	 * Método responsável por excluir um Endereço.
	 * A anotação @Transactional define que este método terá abrir uma transação com o banco de dadosm e caso haja alguem erro ele se encarrega de fazer o Rollback. 
	 * @param id
	 */
	@Transactional
	public void deletar(final Long id){
		this.enderecoRepository.delete(id);
	}
	
	/**
	 * Método responsável por consultar um endereço passando um id.
	 * @param id
	 * @return
	 */
	public Endereco pesquisarPorId(final Long id){
		return this.enderecoRepository.findOneById(id);
	}
	
	/**
	 * Método que lista todos os endereços.
	 * @return
	 */
	public List<Endereco> pesquisarTodos(){
		return this.enderecoRepository.findAll();
	}
}
