package br.com.zupacademy.rodrigo.mercadolivre.security;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


public class LoginRequest {

	@NotBlank
	@Email
	private String login;
	
	@NotBlank
	private String senha;
	
	public LoginRequest(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}

	public UsernamePasswordAuthenticationToken convert(@Valid LoginRequest request) {
		return new UsernamePasswordAuthenticationToken(this.login, this.senha);
	}

}
