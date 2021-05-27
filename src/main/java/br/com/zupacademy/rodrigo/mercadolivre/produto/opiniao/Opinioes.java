package br.com.zupacademy.rodrigo.mercadolivre.produto.opiniao;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Opinioes {

	private Set<OpiniaoProduto> opinioes;

	public Opinioes(Set<OpiniaoProduto> opinioes) {
		this.opinioes = opinioes;
	}

	public <T> Set<T> mapeiaOpinioes(
			Function<OpiniaoProduto, T> funcaoMapeadora) {
		return this.opinioes.stream().map(funcaoMapeadora)
				.collect(Collectors.toSet());
	}
	
	public Double getMedia() {
		Double total = 0.0;
		for (OpiniaoProduto opiniao : opinioes) {
			total += opiniao.getNota();
		}
		if (opinioes.size() == 0) {
			return 0.0;
		}
		return total/opinioes.size();
	}
	
	public Integer getTotal() {
		return this.opinioes.size();
	}
}
