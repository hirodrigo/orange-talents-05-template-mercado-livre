package br.com.zupacademy.rodrigo.mercadolivre.email.fake;

import br.com.zupacademy.rodrigo.mercadolivre.email.Email;
import br.com.zupacademy.rodrigo.mercadolivre.email.EnvioEmail;

public class EnvioFake implements EnvioEmail {

	@Override
	public void enviar(Email email) {
		System.out.println("------------Novo Email--------------");
		System.out.println("Remetente: " + email.getRemetente());
		System.out.println("Destinatário: " + email.getDestinatario());
		System.out.println("Assunto: " + email.getAssunto());
		System.out.println("Mensagem: " + email.getMensagem());
		System.out.println("---------------------------------");

	}

}
