package br.com.zupacademy.rodrigo.mercadolivre.compra.acoes.falha;

import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.rodrigo.mercadolivre.compra.Compra;

public interface AcaoAposFalharCompra {
	
	void executar(Compra compra, UriComponentsBuilder ucb);

}
