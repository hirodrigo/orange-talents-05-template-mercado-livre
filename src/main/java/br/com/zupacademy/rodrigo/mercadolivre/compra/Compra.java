package br.com.zupacademy.rodrigo.mercadolivre.compra;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.rodrigo.mercadolivre.gateway.TipoGateway;
import br.com.zupacademy.rodrigo.mercadolivre.produto.Produto;
import br.com.zupacademy.rodrigo.mercadolivre.usuario.Usuario;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Positive
	private BigInteger quantidade;
	
	@NotNull
	@Positive
	private BigDecimal valor;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoGateway gateway;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusCompra status;
	
	@ManyToOne
	private Produto produto;
	
	@ManyToOne
	private Usuario comprador;

	public Compra(@NotNull @Positive BigInteger quantidade,
			@NotNull TipoGateway gateway, Produto produto, Usuario comprador) {
		this.quantidade = quantidade;
		this.valor = produto.getValor();
		this.gateway = gateway;
		this.produto = produto;
		this.comprador = comprador;
		this.status = StatusCompra.INICIADO;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getLinkPagamento(UriComponentsBuilder ucb) {
		return gateway.gerarCobranca(ucb, this);
	}
	
	public String getEmailDonoProduto() {
		return this.produto.getEmailDono();
	}
}
