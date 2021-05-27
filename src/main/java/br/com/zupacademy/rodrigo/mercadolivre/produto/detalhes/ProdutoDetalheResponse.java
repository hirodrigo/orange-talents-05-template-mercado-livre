package br.com.zupacademy.rodrigo.mercadolivre.produto.detalhes;

import java.math.BigDecimal;
import java.util.Set;

import br.com.zupacademy.rodrigo.mercadolivre.produto.Produto;
import br.com.zupacademy.rodrigo.mercadolivre.produto.opiniao.Opinioes;

public class ProdutoDetalheResponse {

	private String nome;
	
	private BigDecimal valor;
	
	private String descricao;
	
	private Set<CaracteristicaProdutoDetalhe> caracteristicas;
	
	private Set<ImagemProdutoDetalhe> imagens;
	
	private Set<OpiniaoProdutoDetalhe> opinioes;
	
	private Set<PerguntaProdutoDetalhe> perguntas;
	
	private Double mediaNotas;
	
	private Integer numNotas;
	
	public ProdutoDetalheResponse(Produto produto) {
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.valor = produto.getValor();
		this.caracteristicas = produto.mapeiaCaracteristicas(CaracteristicaProdutoDetalhe::new);
		this.imagens = produto.mapeiaImagens(ImagemProdutoDetalhe::new);
		this.perguntas = produto.mapeiaPerguntas(PerguntaProdutoDetalhe::new);
		Opinioes opinioes = produto.getOpinioes();
		this.opinioes = opinioes.mapeiaOpinioes(OpiniaoProdutoDetalhe::new);
		this.mediaNotas = opinioes.getMedia();
		this.numNotas = opinioes.getTotal();
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public Set<CaracteristicaProdutoDetalhe> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<ImagemProdutoDetalhe> getImagens() {
		return imagens;
	}

	public Set<OpiniaoProdutoDetalhe> getOpinioes() {
		return opinioes;
	}

	public Set<PerguntaProdutoDetalhe> getPerguntas() {
		return perguntas;
	}

	public Double getMediaNotas() {
		return mediaNotas;
	}

	public Integer getNumNotas() {
		return numNotas;
	}
	
}
