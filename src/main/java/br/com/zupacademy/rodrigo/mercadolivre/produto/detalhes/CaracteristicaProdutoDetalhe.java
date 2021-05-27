package br.com.zupacademy.rodrigo.mercadolivre.produto.detalhes;

import br.com.zupacademy.rodrigo.mercadolivre.produto.caracteristica.CaracteristicaProduto;

public class CaracteristicaProdutoDetalhe {
	
	private String nome;
	private String descricao;
	
	public CaracteristicaProdutoDetalhe(CaracteristicaProduto caracteristica) {
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
