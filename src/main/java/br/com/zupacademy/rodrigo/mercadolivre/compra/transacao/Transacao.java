package br.com.zupacademy.rodrigo.mercadolivre.compra.transacao;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.rodrigo.mercadolivre.compra.Compra;
import br.com.zupacademy.rodrigo.mercadolivre.gateway.TipoRetornoGateway;

@Entity
public class Transacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String idGateway;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoRetornoGateway status;
	
	@NotNull
	private LocalDateTime instanteProcessamento = LocalDateTime.now(); 
	
	@ManyToOne
	private Compra compra;

	@Deprecated
	public Transacao() {
	}

	public Transacao(@NotNull String idGateway, @NotNull TipoRetornoGateway status, Compra compra) {
		this.idGateway = idGateway;
		this.status = status;
		this.compra = compra;
	}
	
	public boolean transacaoBemSucedida() {
		return status.equals(TipoRetornoGateway.SUCESSO);
	}

}
