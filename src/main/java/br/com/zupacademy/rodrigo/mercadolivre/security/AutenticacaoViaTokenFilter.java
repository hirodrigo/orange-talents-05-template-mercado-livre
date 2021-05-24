package br.com.zupacademy.rodrigo.mercadolivre.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {
	
	private TokenManager tokenManager;
	private UserDetailsServiceImp userDetailsServiceImp;
	
	public AutenticacaoViaTokenFilter(TokenManager tokenManager, UserDetailsServiceImp userDetailsServiceImp) {
		this.tokenManager = tokenManager;
		this.userDetailsServiceImp = userDetailsServiceImp;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recuperarToken(request);
		boolean valido = tokenManager.isTokenValido(token);
		
		if (valido) {
			String userName = tokenManager.getLoginUsuario(token);
            UserDetails userDetails = userDetailsServiceImp.loadUserByUsername(userName);
            UsernamePasswordAuthenticationToken authentication = 
            			new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7, token.length());
	}

}
