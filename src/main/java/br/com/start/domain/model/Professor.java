package br.com.start.domain.model;

import br.com.start.domain.enums.TipoFormacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity(name = "professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(unique = true, nullable = false, length = 200)
    @Email
    private String email;

    @Column(length = 200)
    private String disciplina;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 30, nullable = false)
    private TipoFormacao formacao;

    @Column(nullable = false, precision = 15, scale = 2)
    @PositiveOrZero
    @DecimalMin(value = "0.00", message = "inválido")
    @Digits(integer = 15, fraction = 2, message = "inválido")
    private BigDecimal salario;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate contratacao;
}
