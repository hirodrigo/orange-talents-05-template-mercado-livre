package br.com.zupacademy.rodrigo.mercadolivre.compra.acoes.conclusao;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.zupacademy.rodrigo.mercadolivre.compra.Compra;

@Component
public class IntegracaoSistemaRankingVendedores implements AcaoAposConcluirCompra {

	@Override
	public void executar(Compra compra) {
		RestTemplate restTemplate = new RestTemplate();
		RankingRequest request = new RankingRequest(compra.getIdVendedor(), compra.getId());
		URI uri;
		try {
			uri = new URI("http://localhost:8080/ranking-vendedores");
			restTemplate.postForEntity(uri, request, RankingRequest.class);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

}
