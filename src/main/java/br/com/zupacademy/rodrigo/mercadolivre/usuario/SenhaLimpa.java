package br.com.zupacademy.rodrigo.mercadolivre.usuario;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaLimpa {

	@NotBlank
	@Size(min = 6)
	private String senha;

	/**
	 * @param senha Precisa ser uma senha limpa, sem criptografia.
	 */
	public SenhaLimpa(String senha) {
		this.senha = senha;
	}

	/**
	 * @return Retorna uma senha já criptografada.
	 */
	public String toHash() {
		return new BCryptPasswordEncoder().encode(this.senha);
	}

}
