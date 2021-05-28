package br.com.zupacademy.rodrigo.mercadolivre.compra.acoes.falha;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.rodrigo.mercadolivre.compra.Compra;
import br.com.zupacademy.rodrigo.mercadolivre.email.Email;
import br.com.zupacademy.rodrigo.mercadolivre.email.EnvioEmail;
import br.com.zupacademy.rodrigo.mercadolivre.email.fake.EnvioFake;

@Component
public class EnviarEmailDeFalhaNaCompraParaDono implements AcaoAposFalharCompra {

	@Override
	public void executar(Compra compra, UriComponentsBuilder ucb) {
		String remetente = "api.mercadolivre@orangetalents.com";
		String destinatario = compra.getEmailComprador();
		String assunto = "A sua compra no Mercao Livre falhou.";
		StringBuilder mensagem = new StringBuilder();
		mensagem.append("\nPara tentar pagar novamente, acesse o link: \n");
		mensagem.append(compra.getLinkPagamento(ucb));
		Email email = new Email(remetente, destinatario, assunto, mensagem.toString());
		EnvioEmail envio = new EnvioFake();
		envio.enviar(email);
	}

}
