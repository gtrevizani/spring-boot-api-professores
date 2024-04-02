package br.com.start.api.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.start.api.dto.ProfessorEntradaDto;
import br.com.start.domain.service.ProfessorService;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(path = "professores")
@Log4j2
@Validated
public class ProfessorController {
	@Autowired
	private ProfessorService service;

	@PostMapping
	public ResponseEntity<Object> criar(@Valid @RequestBody ProfessorEntradaDto entradaDto){

		log.info("criar: {}", entradaDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(entradaDto));

	}
	@PutMapping("id/{id}")
	public void alterar(@PathVariable("id") @Min(value=1, message="valor mínimo 1") Long id, @Valid @RequestBody ProfessorEntradaDto entradaDto)  {
		log.info("alterar: {}, {}", id, entradaDto);

		service.alterar(id, entradaDto);
	}
	@GetMapping("id/{id}")
	public ResponseEntity<Object> consultar(@PathVariable("id") @Min(value=1, message="valor mínimo 1") Long id)  {
		log.info("consultar: {}", id);

		return ResponseEntity.ok(service.consultar(id));
	}
	@DeleteMapping("id/{id}")
	public void excluir(@PathVariable("id") @Min(value=1, message="valor mínimo 1") Long id) {
		log.info("excluir: {}", id);

		service.excluir(id);
	}
	@GetMapping
	public ResponseEntity<Object> listar() {
		log.info("listar");

		return ResponseEntity.ok(service.listar());
	}
	

}
