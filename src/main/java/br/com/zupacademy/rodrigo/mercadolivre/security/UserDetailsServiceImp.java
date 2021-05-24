package br.com.zupacademy.rodrigo.mercadolivre.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.zupacademy.rodrigo.mercadolivre.usuario.Usuario;
import br.com.zupacademy.rodrigo.mercadolivre.usuario.UsuarioRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByLogin(username);
		if (usuario.isPresent()) {
			UsuarioLogado usuarioLogado = new UsuarioLogado(usuario.get());
			return usuarioLogado;
		}
		throw new UsernameNotFoundException("Não foi possível encontrar o usuário: " + username);
	}

}
