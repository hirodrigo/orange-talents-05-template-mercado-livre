package br.com.zupacademy.rodrigo.mercadolivre.usuario;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping
	@Transactional
	private ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioRequest request) {
		Usuario usuario = request.toModel();
		usuarioRepository.save(usuario);
		return ResponseEntity.ok().build();
	}

}
