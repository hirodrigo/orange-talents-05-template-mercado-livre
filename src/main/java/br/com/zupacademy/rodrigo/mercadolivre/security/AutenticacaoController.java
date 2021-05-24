package br.com.zupacademy.rodrigo.mercadolivre.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@Profile("default")
public class AutenticacaoController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	TokenManager tokenManager;
	
	@PostMapping
	public ResponseEntity<TokenResponse> autenticar(@RequestBody @Valid LoginRequest request){
		UsernamePasswordAuthenticationToken upat = request.convert(request);
		try {
			Authentication authentication = authenticationManager.authenticate(upat);
			String token = tokenManager.gerarToken(authentication);
			return ResponseEntity.ok(new TokenResponse(token, "Bearer")); 
		}catch(AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
