package br.com.zupacademy.rodrigo.mercadolivre.gateway.paypal;

import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.rodrigo.mercadolivre.compra.Compra;
import br.com.zupacademy.rodrigo.mercadolivre.gateway.Gateway;

public class PayPal implements Gateway {

	@Override
	public String gerarCobranca(UriComponentsBuilder ucb, Compra compra) {
		String redirectUrl = ucb.path("retorno-paypal/{id}").buildAndExpand(compra.getId()).toString();
		return "https://www.paypal.com?returnId=" + compra.getId() + "&redirectUrl=" + redirectUrl;
	}

}
