package br.com.zupacademy.rodrigo.mercadolivre.produto.opiniao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zupacademy.rodrigo.mercadolivre.produto.Produto;
import br.com.zupacademy.rodrigo.mercadolivre.usuario.Usuario;

@Entity
public class OpiniaoProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Min(1)
	@Max(5)
	@NotNull
	private Integer nota;

	@NotBlank
	private String titulo;

	@NotBlank
	@Size(max = 500)
	private String descricao;

	@NotNull
	@ManyToOne
	private Usuario usuario;

	@NotNull
	@ManyToOne
	private Produto produto;

	@Deprecated
	public OpiniaoProduto() {
	}

	public OpiniaoProduto(@Min(1) @Max(5) @NotNull Integer nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao, @NotNull Usuario usuario, @NotNull Produto produto) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}

	public Integer getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
