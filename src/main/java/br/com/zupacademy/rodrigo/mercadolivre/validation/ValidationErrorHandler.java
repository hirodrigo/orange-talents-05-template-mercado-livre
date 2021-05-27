package br.com.zupacademy.rodrigo.mercadolivre.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationErrorHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ValidationErrorResponse> handleValidationError(MethodArgumentNotValidException exception) {
		List<ValidationErrorResponse> dtoList = new ArrayList<ValidationErrorResponse>();

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ValidationErrorResponse error = new ValidationErrorResponse(e.getField(), mensagem);
			dtoList.add(error);
		});

		return dtoList;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public List<ValidationErrorResponse> handleBindError(BindException exception) {
		List<ValidationErrorResponse> dtoList = new ArrayList<ValidationErrorResponse>();

		List<ObjectError> fieldErrors = exception.getAllErrors();
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ValidationErrorResponse error = new ValidationErrorResponse(e.getObjectName(), mensagem);
			dtoList.add(error);
		});

		return dtoList;
	}

}
