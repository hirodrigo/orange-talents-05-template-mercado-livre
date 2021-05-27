package br.com.zupacademy.rodrigo.mercadolivre.produto.detalhes;

import br.com.zupacademy.rodrigo.mercadolivre.produto.imagem.ImagemProduto;

public class ImagemProdutoDetalhe {
	
	private String link;

	public ImagemProdutoDetalhe(ImagemProduto imagem) {
		this.link = imagem.getLink();
	}
	
	public String getLink() {
		return link;
	}
	
}
