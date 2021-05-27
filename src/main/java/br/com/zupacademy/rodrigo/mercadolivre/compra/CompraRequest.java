package br.com.zupacademy.rodrigo.mercadolivre.compra;

import java.math.BigInteger;
import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.validation.BindException;

import br.com.zupacademy.rodrigo.mercadolivre.gateway.TipoGateway;
import br.com.zupacademy.rodrigo.mercadolivre.produto.Produto;
import br.com.zupacademy.rodrigo.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.rodrigo.mercadolivre.usuario.Usuario;
import br.com.zupacademy.rodrigo.mercadolivre.validator.ExistsId;

public class CompraRequest {
	
	@NotNull
	private TipoGateway gateway;
	
	@NotNull
	@ExistsId(domainClass = Produto.class, fieldName = "id")
	private Long idProduto;
	
	@Positive
	@NotNull
	private BigInteger quantidade;
	
	public CompraRequest(@NotNull TipoGateway gateway, @NotNull Long idProduto, @Positive @NotNull BigInteger quantidade) {
		this.gateway = gateway;
		this.idProduto = idProduto;
		this.quantidade = quantidade;
	}
	
	public Long getIdProduto() {
		return idProduto;
	}
	
	public BigInteger getQuantidade() {
		return quantidade;
	}

	public Compra toModel(ProdutoRepository produtoRepository, Usuario comprador) throws BindException {
		Optional<Produto> possivelProduto = produtoRepository.findById(idProduto);
		Produto produto = possivelProduto.get();
		return new Compra(this.quantidade, gateway, produto, comprador);
	}

}
