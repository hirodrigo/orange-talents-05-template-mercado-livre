package br.com.zupacademy.rodrigo.mercadolivre.gateway.pagseguro;

import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.rodrigo.mercadolivre.compra.Compra;
import br.com.zupacademy.rodrigo.mercadolivre.gateway.Gateway;

public class PagSeguro implements Gateway {

	@Override
	public String gerarCobranca(UriComponentsBuilder ucb, Compra compra) {
		String redirectUrl = ucb.path("retorno-paypal/{id}").buildAndExpand(compra.getId()).toString();
		return "https://www.pagseguro.com?returnId=" + compra.getId() + "&redirectUrl=" + redirectUrl;
	}

}
