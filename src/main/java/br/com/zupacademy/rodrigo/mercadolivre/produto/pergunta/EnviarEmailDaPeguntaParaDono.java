package br.com.zupacademy.rodrigo.mercadolivre.produto.pergunta;

import br.com.zupacademy.rodrigo.mercadolivre.email.EnvioEmail;
import br.com.zupacademy.rodrigo.mercadolivre.email.fake.EnvioFake;

public class EnviarEmailDaPeguntaParaDono implements AcaoAposReceberNovaPergunta {
	
	@Override
	public void executar(PerguntaProduto pergunta) {
		EnvioEmail envio = new EnvioFake();
		envio.enviar(pergunta.getTitulo());
	}

}
