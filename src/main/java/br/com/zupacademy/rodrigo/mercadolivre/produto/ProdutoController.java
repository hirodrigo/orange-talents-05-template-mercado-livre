package br.com.zupacademy.rodrigo.mercadolivre.produto;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.rodrigo.mercadolivre.categoria.CategoriaRepository;
import br.com.zupacademy.rodrigo.mercadolivre.security.UsuarioLogado;
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
	private ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoRequest request,
			@AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		Usuario usuario = (usuarioLogado == null ? null : usuarioLogado.getUsuario());

		Produto produto = request.toModel(categoriaRepository, usuario);
		produtoRepository.save(produto);
		return ResponseEntity.ok().build();
	}

}
