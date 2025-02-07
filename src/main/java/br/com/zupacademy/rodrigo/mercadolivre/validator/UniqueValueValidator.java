package br.com.zupacademy.rodrigo.mercadolivre.validator;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

	private String domainAttribute;
	private Class<?> klass;
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void initialize(UniqueValue params) {
		this.domainAttribute = params.fieldName();
		this.klass = params.domainClass();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Query query = entityManager
				.createQuery("SELECT 1 FROM " + klass.getName() + " WHERE " + domainAttribute + " = :value");
		query.setParameter("value", value);
		List<?> list = query.getResultList();
		Assert.state(list.size() <= 1,
				"Foi encontrado mais de um " + klass.getName() + " com o atributo " + domainAttribute + " = " + value);
		return list.isEmpty();

	}

}
