package br.com.zupacademy.rodrigo.mercadolivre.compra.acoes.conclusao;

public class RankingRequest {
	
	private Long idVendedor;
	private Long idCompra;
	
	public RankingRequest(Long idVendedor, Long idCompra) {
		this.idVendedor = idVendedor;
		this.idCompra = idCompra;
	}
	
	public Long getIdCompra() {
		return idCompra;
	}
	
	public Long getIdVendedor() {
		return idVendedor;
	}

}
