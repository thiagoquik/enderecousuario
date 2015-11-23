package br.com.tgolopes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe responsável por lançar uma exceção quando o endereço não for encontrado.
 * Esta classe é orquestrada pela classe ControllerException
 * 
 * @author Thiago Oliveira Lopes
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Endereço não foi encontrado!")
public class EnderecoNaoEncontradoException extends Exception{
	private static final long serialVersionUID = 7889465L;
}
