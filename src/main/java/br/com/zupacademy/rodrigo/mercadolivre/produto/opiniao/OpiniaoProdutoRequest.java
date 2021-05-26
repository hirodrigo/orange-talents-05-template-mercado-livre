package br.com.zupacademy.rodrigo.mercadolivre.produto.opiniao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zupacademy.rodrigo.mercadolivre.produto.Produto;
import br.com.zupacademy.rodrigo.mercadolivre.usuario.Usuario;

public class OpiniaoProdutoRequest {

	@Min(1)
	@Max(5)
	@NotNull
	private Integer nota;

	@NotBlank
	private String titulo;

	@NotBlank
	@Size(max = 500)
	private String descricao;

	public OpiniaoProdutoRequest(@Min(1) @Max(5) @NotNull Integer nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public OpiniaoProduto toModel(Usuario usuario, Produto produto) {
		return new OpiniaoProduto(this.nota, this.titulo, this.descricao, usuario, produto);
	}

}
