package br.com.start.api.conf;

import br.com.start.domain.enums.TabelaDeErros;
import br.com.start.domain.exception.ErroDeNegocioException;
import br.com.start.api.dto.ErroDto;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import lombok.AllArgsConstructor;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(ErroDeNegocioException.class)
	@ResponseBody
	public ResponseEntity<Object> handle(ErroDeNegocioException ex, WebRequest webRequest) {
		var erroDto = new ErroDto();
		erroDto.setErro(ex.getErro());

		return handleExceptionInternal(ex, erroDto, new HttpHeaders(), ex.getHttpStatus(), webRequest);
	}


	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		TabelaDeErros tabela = TabelaDeErros.ERRO_SISTEMA;
		ErroDto erroDto = new ErroDto();
		erroDto.setErro(tabela.getErro());


		return handleExceptionInternal(ex, erroDto, new HttpHeaders(), tabela.getHttpStatus(), request);


	}

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseBody
	//validacao nos parametros
	public ResponseEntity<Object> handle(DataIntegrityViolationException ex, WebRequest webRequest) {
		TabelaDeErros tabela = TabelaDeErros.ERRO_SISTEMA;
		ErroDto erroDto = new ErroDto();
		erroDto.setErro(tabela.getErro());

		return handleExceptionInternal(ex, erroDto, new HttpHeaders(), tabela.getHttpStatus(), webRequest);
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ResponseBody
	@Override
	//validando no JSON
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> validacoes = new ArrayList<>();

		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			validacoes.add(error.getField()+": "+mensagem);
		}

		TabelaDeErros tabela = TabelaDeErros.ERRO_VALIDACAO;

		var erroDto = new ErroDto();
		erroDto.setErro(tabela.getErro());
		erroDto.setValidacoes(validacoes);

		return handleExceptionInternal(ex, erroDto, new HttpHeaders(), tabela.getHttpStatus(),  request);
	}


	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	//validacao nos parametros
	public ResponseEntity<Object> handle(ConstraintViolationException ex, WebRequest webRequest) {
		List<String> validacoes = new ArrayList<>();

		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			String path = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
			validacoes.add(path + ": " + violation.getMessage());
		}

		TabelaDeErros tabela = TabelaDeErros.ERRO_VALIDACAO;
		ErroDto erroDto = new ErroDto();

		erroDto.setErro(tabela.getErro());
		erroDto.setValidacoes(validacoes);

		return handleExceptionInternal(ex, erroDto, new HttpHeaders(), tabela.getHttpStatus(), webRequest);
	}




}
