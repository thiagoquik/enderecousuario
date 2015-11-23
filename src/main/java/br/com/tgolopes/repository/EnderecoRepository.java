package br.com.tgolopes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tgolopes.entity.Endereco;

/**
 * Interface que representa o DAO do Endereço da aplicação, ela que faz a comunicação da transação com o banco de dados.
 * 
 * @author Thiago Oliveira Lopes
 *
 */

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	
	/**
	 * Método responsável por consultar um endereço passando seu número como parâmetro.
	 * 
	 * @param cep
	 * @return
	 */
	Endereco findOneById(Long id);
}
