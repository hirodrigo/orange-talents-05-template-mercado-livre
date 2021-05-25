package br.com.zupacademy.rodrigo.mercadolivre.produto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import br.com.zupacademy.rodrigo.mercadolivre.categoria.Categoria;
import br.com.zupacademy.rodrigo.mercadolivre.categoria.CategoriaRepository;
import br.com.zupacademy.rodrigo.mercadolivre.produto.caracteristica.CaracteristicaProdutoRequest;
import br.com.zupacademy.rodrigo.mercadolivre.usuario.Usuario;
import br.com.zupacademy.rodrigo.mercadolivre.validator.ExistsId;

public class ProdutoRequest {

	@NotBlank
	private String nome;

	@NotNull
	@Positive
	private BigDecimal valor;

	@NotNull
	@PositiveOrZero
	private BigInteger qtdDisponivel;

	@NotNull
	@Size(min = 3)
	private Set<br.com.zupacademy.rodrigo.mercadolivre.produto.caracteristica.CaracteristicaProdutoRequest> caracteristicas;

	@NotBlank
	@Size(max = 1000)
	private String descricao;

	@NotNull
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoria;

	public ProdutoRequest(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
			@NotNull @PositiveOrZero BigInteger qtdDisponivel,
			@NotNull @Size(min = 3) Set<CaracteristicaProdutoRequest> caracteristicas,
			@NotBlank @Size(max = 1000) String descricao, @NotNull Long idCategoria) {
		this.nome = nome;
		this.valor = valor;
		this.qtdDisponivel = qtdDisponivel;
		this.caracteristicas = caracteristicas;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
	}

	public Produto toModel(CategoriaRepository categoriaRepository, Usuario usuario) {
		Optional<Categoria> possivelCategoria = categoriaRepository.findById(idCategoria);
		return new Produto(this.nome, this.valor, this.qtdDisponivel, this.caracteristicas, this.descricao,
				possivelCategoria.get(), usuario);
	}
}
