package br.com.zupacademy.rodrigo.mercadolivre.produto.pergunta;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.rodrigo.mercadolivre.produto.Produto;
import br.com.zupacademy.rodrigo.mercadolivre.usuario.Usuario;

@Entity
public class PerguntaProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String titulo;

	@NotNull
	@ManyToOne
	private Usuario usuario;

	@NotNull
	@ManyToOne
	private Produto produto;
	
	@NotNull
	private LocalDateTime instanteCriacao = LocalDateTime.now();

	@Deprecated
	public PerguntaProduto() {
	}

	public PerguntaProduto(@NotBlank String titulo, @NotNull Usuario usuario, @NotNull Produto produto) {
		this.titulo = titulo;
		this.usuario = usuario;
		this.produto = produto;
	}
	
	public String getTitulo() {
		return titulo;
	}

}
