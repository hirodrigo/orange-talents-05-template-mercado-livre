package br.com.zupacademy.rodrigo.mercadolivre.produto.pergunta;

import br.com.zupacademy.rodrigo.mercadolivre.email.Email;
import br.com.zupacademy.rodrigo.mercadolivre.email.EnvioEmail;
import br.com.zupacademy.rodrigo.mercadolivre.email.fake.EnvioFake;

public class EnviarEmailDaPeguntaParaDono implements AcaoAposReceberNovaPergunta {

	private String remetente = "api.mercadolivre@orangetalents.com";
	private String destinatario;
	private String titulo = "Nova pergunta em seu produto";

	public EnviarEmailDaPeguntaParaDono(String destinatario) {
		this.destinatario = destinatario;
	}
	
	@Override
	public void executar(PerguntaProduto pergunta) {
		EnvioEmail envio = new EnvioFake();
		envio.enviar(new Email(remetente, destinatario, titulo, pergunta.getTitulo()));
	}

}
