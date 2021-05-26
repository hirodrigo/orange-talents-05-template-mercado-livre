package br.com.zupacademy.rodrigo.mercadolivre.uploader;

import java.util.Collection;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

public interface Uploader {
	
	String uploadArquivo(MultipartFile arquivos);
	
	Set<String> uploadArquivos(Collection<MultipartFile> arquivos);

}
