package br.com.zupacademy.rodrigo.mercadolivre.gateway.paypal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.validation.BindException;

import br.com.zupacademy.rodrigo.mercadolivre.compra.Compra;
import br.com.zupacademy.rodrigo.mercadolivre.compra.transacao.Transacao;
import br.com.zupacademy.rodrigo.mercadolivre.gateway.RetornoGateway;
import br.com.zupacademy.rodrigo.mercadolivre.gateway.TipoRetornoGateway;
import br.com.zupacademy.rodrigo.mercadolivre.validator.UniqueValue;

public class TransacaoPayPalRequest implements RetornoGateway  {
	
	@NotBlank
	@UniqueValue(domainClass = Transacao.class, fieldName = "idGateway")
	private String idTransacao;
	
	@NotNull
	@Min(0)
	@Max(1)
	private Integer statusTransacao;
	
	public TransacaoPayPalRequest(@NotBlank String idTransacao, @NotBlank Integer statusTransacao) {
		this.idTransacao = idTransacao;
		this.statusTransacao = statusTransacao;
	}

	@Override
	public Transacao toModel(Compra compra) throws BindException {
		TipoRetornoGateway status = converteStatus();
		
		if (compra.compraEstaPaga() && status.equals(TipoRetornoGateway.SUCESSO)) {
			BindException compraPaga = new BindException(this.statusTransacao, "status");
			compraPaga.reject(null, "Essa compra já foi paga anteriormente: " + compra.getId());
			throw compraPaga;
		}
		return new Transacao(idTransacao, status, compra);
	}
	
	public TipoRetornoGateway converteStatus() {
		switch (statusTransacao) {
		case 1:
			return TipoRetornoGateway.SUCESSO;
		default:
			return TipoRetornoGateway.FALHA;
		}
	}


}
