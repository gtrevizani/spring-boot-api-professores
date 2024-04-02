package br.com.start.api.dto;

import javax.validation.Valid;
import javax.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Valid
public class ProfessorEntradaDto {
	protected static final String REGEX_ENUM = "PEDAGOGO|LICENCIATURA|MESTRE|DOUTOR";
	protected static final String REGEX_DATA = "^(?:(?:29([/])02(?:\\1)(?:(?:(?:1[6-9]|20)(?:04|08|[2468][048]|[13579][26]))|(?:1600|2[048]00)))|(?:(?:(?:0[1-9]|1\\d|2[0-8])([/])(?:0[1-9]|1[0-2]))|(?:29|30)([/])(?:0(?:1|[3-9])|(?:1[0-2]))|31([/])(0[13578]|1[02]))(?:\\2|\\3|\\4)(?:1[6-9]|2\\d)\\d\\d)$";
	protected static final String REGEX_SALARIO = "^(\\d*\\.)?\\d+$";

	@NotBlank(message="obrigatório")
	@Size(min=2, max=200, message="máximo 200 caracteres")
	private String nome;

	@NotBlank(message="obrigatório")
	@Size(min=1, max=200, message="máximo 200 caracteres")
	@Email(message="inválido")
	private String email;
	
	@Size(min=1, max=200, message="máximo 200 caracteres")
	private String disciplina;
	
	@NotBlank(message="obrigatório")
	@Pattern(regexp = REGEX_ENUM, message="inválido")
	private String formacao;
	
	@NotBlank(message="obrigatório")
	@Pattern(regexp = REGEX_SALARIO, message = "inválido")
	private String salario;

	@NotBlank(message="obrigatório")
	@Pattern(regexp = REGEX_DATA, message = "inválido")
	private String contratacao;


}
