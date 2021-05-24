package br.com.zupacademy.rodrigo.mercadolivre.security;

public class TokenResponse {
	
	private String tipo;
	private String token;

	public TokenResponse(String token, String tipo) {
		this.tipo = tipo;
		this.token = token;
	}

	public String getTipo() {
		return tipo;
	}

	public String getToken() {
		return token;
	}

}
