package br.com.zupacademy.rodrigo.mercadolivre.produto.detalhes;

import br.com.zupacademy.rodrigo.mercadolivre.produto.opiniao.OpiniaoProduto;

public class OpiniaoProdutoDetalhe {
	
	private Integer nota;
	private String titulo;
	private String descricao;
	
	public OpiniaoProdutoDetalhe(OpiniaoProduto opiniao) {
		this.nota = opiniao.getNota();
		this.titulo = opiniao.getTitulo();
		this.descricao = opiniao.getDescricao();
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
