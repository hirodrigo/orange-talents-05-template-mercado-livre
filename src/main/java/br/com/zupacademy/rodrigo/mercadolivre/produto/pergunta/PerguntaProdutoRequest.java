package br.com.zupacademy.rodrigo.mercadolivre.produto.pergunta;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.rodrigo.mercadolivre.produto.Produto;
import br.com.zupacademy.rodrigo.mercadolivre.usuario.Usuario;

public class PerguntaProdutoRequest {

	@NotBlank
	private String titulo;

	public void setTitulo(@NotBlank String titulo) {
		this.titulo = titulo;
	}

	public PerguntaProduto toModel(Usuario usuario, Produto produto) {
		return new PerguntaProduto(this.titulo, usuario, produto);
	}

}
