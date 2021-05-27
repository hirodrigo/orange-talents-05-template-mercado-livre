package br.com.zupacademy.rodrigo.mercadolivre.gateway;

import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.rodrigo.mercadolivre.compra.Compra;

public interface Gateway {
	
	String gerarCobranca(UriComponentsBuilder ucb, Compra compra);

}
