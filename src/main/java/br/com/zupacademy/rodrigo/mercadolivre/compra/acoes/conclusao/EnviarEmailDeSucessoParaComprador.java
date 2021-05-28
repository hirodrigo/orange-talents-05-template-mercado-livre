package br.com.zupacademy.rodrigo.mercadolivre.compra.acoes.conclusao;

import org.springframework.stereotype.Component;

import br.com.zupacademy.rodrigo.mercadolivre.compra.Compra;
import br.com.zupacademy.rodrigo.mercadolivre.email.Email;
import br.com.zupacademy.rodrigo.mercadolivre.email.EnvioEmail;
import br.com.zupacademy.rodrigo.mercadolivre.email.fake.EnvioFake;

@Component
public class EnviarEmailDeSucessoParaComprador implements AcaoAposConcluirCompra {

	@Override
	public void executar(Compra compra) {
		String remetente = "api.mercadolivre@orangetalents.com";
		String destinatario = compra.getEmailComprador();
		String assunto = "Compra concluída com sucesso!";
		StringBuilder mensagem = new StringBuilder();
		mensagem.append("A sua compra do produto: ");
		mensagem.append(compra.getNomeProduto());
		mensagem.append("\n Quantidade de unidades: ");
		mensagem.append(compra.getQuantidade());
		mensagem.append("\n Valor da unidade: ");
		mensagem.append(compra.getValor());
		mensagem.append("\n Valor da unidade: ");
		mensagem.append("Foi concluída com sucesso!");
		Email email = new Email(remetente, destinatario, assunto, mensagem.toString());
		EnvioEmail envio = new EnvioFake();
		envio.enviar(email);
	}

}
