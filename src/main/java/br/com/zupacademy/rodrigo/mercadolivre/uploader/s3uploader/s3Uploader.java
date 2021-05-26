package br.com.zupacademy.rodrigo.mercadolivre.uploader.s3uploader;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import br.com.zupacademy.rodrigo.mercadolivre.uploader.Uploader;

public class s3Uploader implements Uploader {

	@Override
	public Set<String> uploadArquivos(Collection<MultipartFile> arquivos) {
		Set<String> links = new HashSet<String>();

		for (MultipartFile arquivo : arquivos) {
			String link = uploadoToS3Fake(arquivo);
			links.add(link);
		}

		return links;
	}
	
	@Override
	public String uploadArquivo(MultipartFile arquivo) {
		return uploadoToS3Fake(arquivo);
	}

	private String uploadoToS3Fake(MultipartFile arquivo) {
		StringBuilder sb = new StringBuilder();

		sb.append("https://linksuperverdadeiro.s3bucket.com/");
		sb.append(UUID.randomUUID());

		return sb.toString();
	}

}
