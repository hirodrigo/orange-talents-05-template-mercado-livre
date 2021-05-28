package br.com.zupacademy.rodrigo.mercadolivre.gateway.pagseguro;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.rodrigo.mercadolivre.compra.Compra;
import br.com.zupacademy.rodrigo.mercadolivre.compra.CompraRepository;
import br.com.zupacademy.rodrigo.mercadolivre.compra.acoes.conclusao.AcaoAposConcluirCompra;
import br.com.zupacademy.rodrigo.mercadolivre.compra.acoes.falha.AcaoAposFalharCompra;
import br.com.zupacademy.rodrigo.mercadolivre.compra.transacao.Transacao;

@RestController
public class ControllerGatewayPagSeguro {

	@Autowired
	CompraRepository compraRespository;

	@Autowired
	private Set<AcaoAposConcluirCompra> acaoAposNovaCompra;

	@Autowired
	private Set<AcaoAposFalharCompra> acaoAposFalharCompra;

	@PostMapping(path = { "retorno-pagseguro/{idCompra}" })
	@Transactional
	private ResponseEntity<?> cadastrarTransacao(@PathVariable Long idCompra,
			@RequestBody @Valid TransacaoPagSeguroRequest request, UriComponentsBuilder ucb) throws BindException {
		Optional<Compra> possivelCompra = compraRespository.findById(idCompra);

		if (possivelCompra.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Compra compra = possivelCompra.get();

		Transacao transacao = request.toModel(compra);
		compra.adicionarTransacao(transacao);

		compraRespository.save(compra);

		if (compra.compraEstaPaga()) {
			acaoAposNovaCompra.forEach(acao -> acao.executar(compra));
		} else {
			acaoAposFalharCompra.forEach(acao -> acao.executar(compra, ucb));
		}

		return ResponseEntity.ok().build();
	}

}
