package br.com.zupacademy.rodrigo.mercadolivre.gateway;

import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.rodrigo.mercadolivre.compra.Compra;
import br.com.zupacademy.rodrigo.mercadolivre.gateway.pagseguro.PagSeguro;
import br.com.zupacademy.rodrigo.mercadolivre.gateway.paypal.PayPal;

public enum TipoGateway {
	
	PAGSEGURO(new PagSeguro()),
	PAYPAL(new PayPal());
	
	private Gateway gateway;
	
	private TipoGateway(Gateway gateway) {
		this.gateway = gateway;
	}
	
	public String gerarCobranca(UriComponentsBuilder ucb, Compra compra) {
		return this.gateway.gerarCobranca(ucb, compra);
	}

}
