package br.com.tgolopes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Classe responsável por controlar as páginas da aplicação.
 * 
 * @author Thiago Oliveira Lopes
 *
 */

@Controller
@RequestMapping(value = "/")
public class ViewController {
	
	/**
	 * Método responsável por chamar via GET a página exibirEnderecos.html, sem passar nada como parâmetro no browser.
	 * @return
	 */
    @RequestMapping(method = RequestMethod.GET)
    public String abrirPaginaExibirEnderecos() {
        return "exibirEnderecos";
    }
    
    /**
     * Método responsável por chamar via GET a página exibirEnderecos.html, passando exibirEnderecos via GET.
     * @return
     */
    @RequestMapping(value = "exibirEnderecos", method = RequestMethod.GET)
    public String abrirPaginaTodosOsEnderecos() {
        return "exibirEnderecos";
    }
    
    /**
     * Método responsável por chamar via GET a página gerenciarEndereco.html, passando gerenciarEndereco via GET.
     * @return
     */
    @RequestMapping(value = "gerenciarEndereco", method = RequestMethod.GET)
    public String mostrarCadastro() {
        return "gerenciarEndereco";
    }

    /**
     * Método responsável por chamar via GET a página gerenciarEndereco.html, passando alterar/{id} via GET.
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "alterar/{id}", method = RequestMethod.GET)
    public String alterarCadastro(@PathVariable Long id, Model model) {
        if (id != null) {
            model.addAttribute("id", id);
        }
        return "gerenciarEndereco";
    }
}