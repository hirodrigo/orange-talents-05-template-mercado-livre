package br.com.zupacademy.rodrigo.mercadolivre.validation;

public class ValidationErrorResponse {

	private String campo;
	private String erro;

	public ValidationErrorResponse(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

}
