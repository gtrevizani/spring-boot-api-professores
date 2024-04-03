package br.com.start.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.start.domain.enums.TipoFormacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
public class ProfessorSaidaDto {

	private Long id;
	private String nome;
	private String email;
	private String disciplina;
	private TipoFormacao formacao;
	private BigDecimal salario;
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate contratacao;

}
