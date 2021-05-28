package br.com.zupacademy.rodrigo.mercadolivre.gateway.pagseguro;

import javax.validation.constraints.NotBlank;

import org.springframework.validation.BindException;

import br.com.zupacademy.rodrigo.mercadolivre.compra.Compra;
import br.com.zupacademy.rodrigo.mercadolivre.compra.transacao.Transacao;
import br.com.zupacademy.rodrigo.mercadolivre.gateway.RetornoGateway;
import br.com.zupacademy.rodrigo.mercadolivre.gateway.TipoRetornoGateway;
import br.com.zupacademy.rodrigo.mercadolivre.validator.UniqueValue;

public class TransacaoPagSeguroRequest implements RetornoGateway {
	
	@NotBlank
	@UniqueValue(domainClass = Transacao.class, fieldName = "idGateway")
	private String idTransacao;
	
	@NotBlank
	private String statusTransacao;
	
	public TransacaoPagSeguroRequest(@NotBlank String idTransacao, @NotBlank String statusTransacao) {
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
		case "SUCESSO":
			return TipoRetornoGateway.SUCESSO;
		default:
			return TipoRetornoGateway.FALHA;
		}
	}

}
