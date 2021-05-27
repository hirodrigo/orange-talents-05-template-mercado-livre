package br.com.zupacademy.rodrigo.mercadolivre.email.fake;

import br.com.zupacademy.rodrigo.mercadolivre.email.EnvioEmail;

public class EnvioFake implements EnvioEmail {

	@Override
	public void enviar(String mensagem) {
		System.out.println("---------------------------------");
		System.out.println("Mensagem: " + mensagem);
		System.out.println("---------------------------------");
	}

}
