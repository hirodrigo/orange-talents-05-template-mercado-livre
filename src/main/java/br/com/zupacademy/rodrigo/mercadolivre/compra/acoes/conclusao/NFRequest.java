package br.com.zupacademy.rodrigo.mercadolivre.compra.acoes.conclusao;

public class NFRequest {
	
	private Long idComprador;
	private Long idCompra;
	
	public NFRequest(Long idComprador, Long idCompra) {
		this.idComprador = idComprador;
		this.idCompra = idCompra;
	}
	
	public Long getIdCompra() {
		return idCompra;
	}
	
	public Long getIdComprador() {
		return idComprador;
	}

}
