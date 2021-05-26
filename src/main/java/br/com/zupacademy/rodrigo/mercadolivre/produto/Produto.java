package br.com.zupacademy.rodrigo.mercadolivre.produto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import br.com.zupacademy.rodrigo.mercadolivre.categoria.Categoria;
import br.com.zupacademy.rodrigo.mercadolivre.produto.caracteristica.CaracteristicaProduto;
import br.com.zupacademy.rodrigo.mercadolivre.produto.imagem.ImagemProduto;
import br.com.zupacademy.rodrigo.mercadolivre.usuario.Usuario;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<CaracteristicaProduto> caracteristicas;

	@NotBlank
	@Size(max = 1000)
	private String descricao;

	@NotNull
	private LocalDateTime instanteCadastro = LocalDateTime.now();

	@NotNull
	@ManyToOne
	private Categoria categoria;

	@ManyToOne
	private Usuario usuario;
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<@Valid ImagemProduto> imagens;

	@Deprecated
	public Produto() {
	}

	public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
			@NotNull @PositiveOrZero BigInteger qtdDisponivel,
			@NotNull @Size(min = 3) Set<br.com.zupacademy.rodrigo.mercadolivre.produto.caracteristica.CaracteristicaProdutoRequest> caracteristicas,
			@NotBlank @Size(max = 1000) String descricao, @NotNull Categoria categoria, Usuario usuario) {
		this.nome = nome;
		this.valor = valor;
		this.qtdDisponivel = qtdDisponivel;
		this.caracteristicas = caracteristicas.stream().map(caracteristica -> caracteristica.toModel(this))
				.collect(Collectors.toSet());
		this.descricao = descricao;
		this.categoria = categoria;
		this.usuario = usuario;

		Assert.isTrue(this.caracteristicas.size() >= 3, "Produtos precisam ter 3 categorias ou mais.");
		Assert.isTrue(this.categoria != null, "Produtos pertencer a uma categoria.");
	}
	
	public boolean pertenceAoUsuario(Usuario usuario) {
		return this.usuario.equals(usuario);
	}

	public void adicionarImagens(Collection<String> links) {
		for (String link : links) {
			ImagemProduto imagem = new ImagemProduto(link, this);
			this.imagens.add(imagem);
		}
	}

}
