package br.com.zupacademy.rodrigo.mercadolivre.produto;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.rodrigo.mercadolivre.categoria.CategoriaRepository;
import br.com.zupacademy.rodrigo.mercadolivre.produto.imagem.ImagensProdutoRequest;
import br.com.zupacademy.rodrigo.mercadolivre.produto.opiniao.OpiniaoProduto;
import br.com.zupacademy.rodrigo.mercadolivre.produto.opiniao.OpiniaoProdutoRequest;
import br.com.zupacademy.rodrigo.mercadolivre.security.UsuarioLogado;
import br.com.zupacademy.rodrigo.mercadolivre.uploader.Uploader;
import br.com.zupacademy.rodrigo.mercadolivre.uploader.s3uploader.s3Uploader;
import br.com.zupacademy.rodrigo.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@PostMapping
	@Transactional
	private ResponseEntity<?> cadastrarProduto(@RequestBody @Valid ProdutoRequest request,
			@AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		Usuario usuario = usuarioLogado.getUsuario();

		Produto produto = request.toModel(categoriaRepository, usuario);
		produtoRepository.save(produto);
		return ResponseEntity.ok().build();
	}

	@PostMapping(path = { "/{id}/imagens" })
	@Transactional
	private ResponseEntity<?> cadastrarImagemProduto(@PathVariable Long id, @Valid ImagensProdutoRequest request,
			@AuthenticationPrincipal UsuarioLogado usuarioLogado) {

		Optional<Produto> possivelProduto = produtoRepository.findById(id);
		if (possivelProduto.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Produto produto = possivelProduto.get();

		Usuario usuario = usuarioLogado.getUsuario();
		if (!produto.pertenceAoUsuario(usuario)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		Uploader uploader = new s3Uploader();
		Set<String> links = uploader.uploadArquivos(request.getImagens());
		produto.adicionarImagens(links);

		produtoRepository.save(produto);

		return ResponseEntity.ok().build();
	}

	@PostMapping(path = { "/{id}/opinioes" })
	@Transactional
	private ResponseEntity<?> cadastrarOpiniaoProduto(@PathVariable Long id,
			@RequestBody @Valid OpiniaoProdutoRequest request, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {

		Optional<Produto> possivelProduto = produtoRepository.findById(id);
		if (possivelProduto.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Usuario usuario = usuarioLogado.getUsuario();
		Produto produto = possivelProduto.get();

		OpiniaoProduto opiniao = request.toModel(usuario, produto);

		produto.adicionarOpiniao(opiniao);

		produtoRepository.save(produto);

		return ResponseEntity.ok().build();
	}

}
