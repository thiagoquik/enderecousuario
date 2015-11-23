package br.com.tgolopes.dto;

/**
 * Classe que representa a estrutura de Cep para consumir o serviço da aplicação pesquisa-cep.
 * 
 * @author Thiago Oliveira Lopes
 *
 */
public class CepDTO {
	private Long id;
	private String numCep;
	private String logradouro;
	private String bairro;
	private String cidade;
	private String estado;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumCep() {
		return numCep;
	}
	public void setNumCep(String numCep) {
		this.numCep = numCep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
