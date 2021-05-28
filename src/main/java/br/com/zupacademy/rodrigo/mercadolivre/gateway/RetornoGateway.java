package br.com.zupacademy.rodrigo.mercadolivre.gateway;

import org.springframework.validation.BindException;

import br.com.zupacademy.rodrigo.mercadolivre.compra.Compra;
import br.com.zupacademy.rodrigo.mercadolivre.compra.transacao.Transacao;

public interface RetornoGateway {
	
	Transacao toModel(Compra compra) throws BindException;

}
