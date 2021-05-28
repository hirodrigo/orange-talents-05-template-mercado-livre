package br.com.zupacademy.rodrigo.mercadolivre.compra;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.rodrigo.mercadolivre.compra.transacao.Transacao;
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
	
	@OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
	private Set<Transacao> transacoes;

	@Deprecated
	public Compra() {
	}

	public Compra(@NotNull @Positive BigInteger quantidade,
			@NotNull TipoGateway gateway, Produto produto, Usuario comprador) {
		this.quantidade = quantidade;
		this.valor = produto.getValor();
		this.gateway = gateway;
		this.produto = produto;
		this.comprador = comprador;
		this.status = StatusCompra.INICIADA;
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
	
	public String getEmailComprador() {
		return this.comprador.getLogin();
	}
	
	public String getNomeProduto() {
		return this.produto.getNome();
	}
	
	public Long getIdComprador() {
		return this.comprador.getId();
	}
	
	public Long getIdVendedor() {
		return this.produto.getIdDono();
	}
	
	public BigInteger getQuantidade() {
		return quantidade;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public boolean compraEstaPaga() {
		return this.status == StatusCompra.PAGA;
	}
	
	public void adicionarTransacao(Transacao transacao) {
		this.transacoes.add(transacao);
		if (transacao.transacaoBemSucedida()) {
			this.status = StatusCompra.PAGA;
		}
	}
}
