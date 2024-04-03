package br.com.start.domain.service;


import java.util.List;
import java.util.Optional;

import br.com.start.api.assembler.ProfessorMapper;
import br.com.start.domain.enums.TabelaDeErros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.start.domain.exception.ErroDeNegocioException;
import br.com.start.domain.model.Professor;
import br.com.start.api.dto.ProfessorEntradaDto;
import br.com.start.api.dto.ProfessorSaidaDto;
import br.com.start.domain.repository.ProfessorRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    @Autowired
    private ProfessorMapper mapper;

    @Transactional
    public ProfessorSaidaDto criar(ProfessorEntradaDto entradaDto)  {

        if (repository.existsByEmail(entradaDto.getEmail())) {
            throw new ErroDeNegocioException(TabelaDeErros.EMAIL_JA_CADASTRADO);
        }

        mapper.parseStringToLocalDate(entradaDto.getContratacao());
        ProfessorSaidaDto professorSaidaDto = mapper.parseInputToOutput(entradaDto);
        Professor professor = mapper.parseOutputToEntity(professorSaidaDto);
        Professor professorBanco = repository.save(professor);
        return mapper.parseEntityToOutput(professorBanco);

    }

    @Transactional
    public void alterar(Long id, ProfessorEntradaDto entradaDto)  {
        Optional<Professor> optional = repository.findById(id);

        if (optional.isEmpty()) {
            throw new ErroDeNegocioException(TabelaDeErros.PROFESSOR_NAO_ENCONTRADO);
        }

        if (repository.existsByEmail(entradaDto.getEmail())) {
            throw new ErroDeNegocioException(TabelaDeErros.EMAIL_JA_CADASTRADO);
        }

        Professor professor = optional.get();
        mapper.parseInputToEntity(entradaDto, professor);
        repository.save(professor);
    }
    public ProfessorSaidaDto consultar(Long id)  {
        Optional<Professor> optional = repository.findById(id);

        if (optional.isEmpty()) {
            throw new ErroDeNegocioException(TabelaDeErros.PROFESSOR_NAO_ENCONTRADO);
        }

        Professor professorBanco = optional.get();
        return mapper.parseEntityToOutput(professorBanco);
    }
    public void excluir(Long id)  {

        if (!repository.existsById(id)) {
            throw new ErroDeNegocioException(TabelaDeErros.SEM_DADOS);
        }

        repository.deleteById(id);
    }
    public List<ProfessorSaidaDto> listar() {
        List<Professor> professor = repository.findAll();

        if(professor.isEmpty()){
            throw new ErroDeNegocioException(TabelaDeErros.SEM_DADOS);
        }

        return mapper.parseEntityListToOutputList(professor);
    }
}
