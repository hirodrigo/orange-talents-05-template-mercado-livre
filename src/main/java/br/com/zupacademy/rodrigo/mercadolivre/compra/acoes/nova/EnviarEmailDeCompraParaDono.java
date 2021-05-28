package br.com.zupacademy.rodrigo.mercadolivre.compra.acoes.nova;

import org.springframework.stereotype.Component;

import br.com.zupacademy.rodrigo.mercadolivre.compra.Compra;
import br.com.zupacademy.rodrigo.mercadolivre.email.Email;
import br.com.zupacademy.rodrigo.mercadolivre.email.EnvioEmail;
import br.com.zupacademy.rodrigo.mercadolivre.email.fake.EnvioFake;

@Component
public class EnviarEmailDeCompraParaDono implements AcaoAposReceberNovaCompra {

	@Override
	public void executar(Compra compra) {
		String remetente = "api.mercadolivre@orangetalents.com";
		String destinatario = compra.getEmailDonoProduto();
		String assunto = "Nova compra na sua loja do Mercado Livre!";
		String mensagem = "Uma nova compra foi feita em sua loja do Mercado Livre.";
		Email email = new Email(remetente, destinatario, assunto, mensagem);
		EnvioEmail envio = new EnvioFake();
		envio.enviar(email);
	}

}
