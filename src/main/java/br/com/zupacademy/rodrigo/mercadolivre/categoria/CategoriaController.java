package br.com.zupacademy.rodrigo.mercadolivre.categoria;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@PostMapping
	@Transactional
	private ResponseEntity<?> cadastrar(@RequestBody @Valid CategoriaRequest request) {
		Categoria categoria = request.toModel(categoriaRepository);
		categoriaRepository.save(categoria);
		return ResponseEntity.ok().build();
	}

}
