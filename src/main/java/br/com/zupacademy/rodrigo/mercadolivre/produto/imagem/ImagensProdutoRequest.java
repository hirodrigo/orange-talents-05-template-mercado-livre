package br.com.zupacademy.rodrigo.mercadolivre.produto.imagem;

import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class ImagensProdutoRequest {
	
	@NotNull
	@Size(min = 1)
	private Set<MultipartFile> imagens;
	
	public void setImagens(Set<MultipartFile> imagens) {
		this.imagens = imagens;
	}
	
	public Set<MultipartFile> getImagens() {
		return imagens;
	}

}
