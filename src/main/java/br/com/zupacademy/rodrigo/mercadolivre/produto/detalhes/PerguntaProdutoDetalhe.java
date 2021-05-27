package br.com.zupacademy.rodrigo.mercadolivre.produto.detalhes;

import br.com.zupacademy.rodrigo.mercadolivre.produto.pergunta.PerguntaProduto;

public class PerguntaProdutoDetalhe {
	
	private String titulo;
	
	public PerguntaProdutoDetalhe(PerguntaProduto pergunta) {
		this.titulo = pergunta.getTitulo();
	}

	public String getTitulo() {
		return titulo;
	}
	
}
