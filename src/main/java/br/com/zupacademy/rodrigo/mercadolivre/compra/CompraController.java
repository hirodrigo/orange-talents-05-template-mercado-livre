package br.com.zupacademy.rodrigo.mercadolivre.compra;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.rodrigo.mercadolivre.compra.acoes.nova.AcaoAposReceberNovaCompra;
import br.com.zupacademy.rodrigo.mercadolivre.compra.acoes.nova.EnviarEmailDeCompraParaDono;
import br.com.zupacademy.rodrigo.mercadolivre.produto.Produto;
import br.com.zupacademy.rodrigo.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.rodrigo.mercadolivre.security.UsuarioLogado;
import br.com.zupacademy.rodrigo.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping("/compras")
public class CompraController {

	@Autowired
	private CompraRepository compraRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@PostMapping
	@Transactional
	private ResponseEntity<?> cadastrar(@RequestBody @Valid CompraRequest request,
			@AuthenticationPrincipal UsuarioLogado usuarioLogado, UriComponentsBuilder ucb) throws BindException {
		Usuario usuario = usuarioLogado.getUsuario();

		Optional<Produto> possivelProduto = produtoRepository.findById(request.getIdProduto());
		Produto produto = possivelProduto.get();

		boolean reduziuQuantidade = produto.reduzirQuantidade(request.getQuantidade());

		if (reduziuQuantidade) {
			produtoRepository.save(produto);

			Compra compra = request.toModel(produtoRepository, usuario);
			compraRepository.save(compra);

			AcaoAposReceberNovaCompra enviarEmail = new EnviarEmailDeCompraParaDono();
			enviarEmail.executar(compra);

			String link = compra.getLinkPagamento(ucb);
			return ResponseEntity.status(HttpStatus.FOUND).header("Locationx", link).build();
		}
		BindException estoqueIndisponivel = new BindException(request.getQuantidade(), "quantidade");
		estoqueIndisponivel.reject(null, "Quantidade indisponível no estoque: " + request.getQuantidade());
		throw estoqueIndisponivel;
	}

}
