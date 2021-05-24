package br.com.zupacademy.rodrigo.mercadolivre.usuario;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Email
	private String login;

	@NotBlank
	@Size(min = 6)
	private String senha;

	@PastOrPresent
	private LocalDateTime instanteCadastro;

	@Deprecated
	public Usuario() {
	}

	/**
	 * @param login Precisa ter formato de um e-mail válido.
	 */
	public Usuario(@NotBlank @Email String login, SenhaLimpa senhaLimpa) {
		this.login = login;
		this.senha = senhaLimpa.toHash();
		this.instanteCadastro = LocalDateTime.now();
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

}
