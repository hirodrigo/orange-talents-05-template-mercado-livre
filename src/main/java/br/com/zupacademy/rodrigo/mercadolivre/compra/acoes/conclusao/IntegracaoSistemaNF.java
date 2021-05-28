package br.com.zupacademy.rodrigo.mercadolivre.compra.acoes.conclusao;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.zupacademy.rodrigo.mercadolivre.compra.Compra;

@Component
public class IntegracaoSistemaNF implements AcaoAposConcluirCompra {

	@Override
	public void executar(Compra compra) {
		RestTemplate restTemplate = new RestTemplate();
		NFRequest response = new NFRequest(compra.getIdComprador(), compra.getId());
		URI uri;
		try {
			uri = new URI("http://localhost:8080/notas-fiscais");
			restTemplate.postForEntity(uri, response, NFRequest.class);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

}
