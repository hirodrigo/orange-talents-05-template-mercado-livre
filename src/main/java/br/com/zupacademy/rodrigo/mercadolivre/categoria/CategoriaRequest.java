package br.com.zupacademy.rodrigo.mercadolivre.categoria;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.rodrigo.mercadolivre.validator.ExistsId;
import br.com.zupacademy.rodrigo.mercadolivre.validator.UniqueValue;

public class CategoriaRequest {

	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;

	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoriaMae;

	public CategoriaRequest(@NotBlank String nome, Long idCategoriaMae) {
		this.nome = nome;
		this.idCategoriaMae = idCategoriaMae;
	}

	public Categoria toModel(CategoriaRepository categoriaRepository) {
		Categoria categoria = new Categoria(nome);

		if (idCategoriaMae != null) {
			Optional<Categoria> possivelCategoriaMae = categoriaRepository.findById(idCategoriaMae);
			
			if (possivelCategoriaMae.isPresent()) {
				categoria.setCategoriaMae(possivelCategoriaMae.get());
			}
		}
		
		return categoria;
	}

}
