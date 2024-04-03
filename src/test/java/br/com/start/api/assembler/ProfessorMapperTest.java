package br.com.start.api.assembler;

import br.com.start.api.dto.ProfessorEntradaDto;
import br.com.start.api.dto.ProfessorSaidaDto;
import br.com.start.domain.model.Professor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProfessorMapperTest {

    @InjectMocks
    private ProfessorMapper professorMapper;

    @Mock
    private ModelMapper modelMapper;

    @BeforeAll
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void parseProfessorSaidaDto_toProfessor(){

        ProfessorSaidaDto professorOutput = new ProfessorSaidaDto();
        professorOutput.setNome("Guilherme");

        Professor professor = new Professor();
        professor.setNome("Guilherme");

        Mockito.when(modelMapper.map(Mockito.eq(professorOutput), Mockito.eq(Professor.class))).thenReturn(professor);

        Professor professorResult = professorMapper.parseOutputToEntity(professorOutput);

        Assertions.assertNotNull(professorResult);
        Assertions.assertEquals(professor.getNome(), professorResult.getNome());

    }

    @Test
    public void parseProfessor_toProfessorSaidaDtoSave(){

//        Professor professor = Mockito.mock(Professor.class, Answers.RETURNS_MOCKS);

        Professor professor = new Professor();
        professor.setNome("Guilherme");

        ProfessorSaidaDto professorSaida = new ProfessorSaidaDto();
        professorSaida.setNome("Guilherme");

        Mockito.when(modelMapper.map(Mockito.eq(professor), Mockito.eq(ProfessorSaidaDto.class))).thenReturn(professorSaida);


        ProfessorSaidaDto professorResult = professorMapper.parseEntityToOutput(professor);

        Assertions.assertNotNull(professorResult);
        Assertions.assertEquals(professor.getNome(), professorResult.getNome());

    }

    @Test
    public void parseProfessorEntradaDto_toProfessorSaidaDto(){
        ProfessorEntradaDto professor = new ProfessorEntradaDto();
        professor.setContratacao("10/10/2024");
        professor.setNome("Guilherme");

        ProfessorSaidaDto professorSaida = new ProfessorSaidaDto();
        professorSaida.setNome("Guilherme");

        Mockito.when(modelMapper.map(Mockito.eq(professor), Mockito.eq(ProfessorSaidaDto.class))).thenReturn(professorSaida);


        ProfessorSaidaDto professorResult = professorMapper.parseInputToOutput(professor);


        Assertions.assertNotNull(professorResult);
        Assertions.assertEquals(professor.getNome(), professorResult.getNome());

    }

    @Test
    public void parseProfessorEntradaDto_toProfessorOptional(){

        ProfessorEntradaDto professor = Mockito.mock(ProfessorEntradaDto.class, Answers.RETURNS_MOCKS);
        Professor professor1 = Mockito.mock(Professor.class, Answers.RETURNS_MOCKS);

        professorMapper.parseInputToEntity(professor, professor1);

        Assertions.assertNotNull(professor1);
        Assertions.assertEquals(professor.getNome(), professor1.getNome());

    }

    @Test
    public void parseListProfessor_toListProfessorSaidaDto(){
        List<Professor> professor = Collections.singletonList(Mockito.mock(Professor.class, Answers.RETURNS_MOCKS));
        Mockito.when(modelMapper.map(Mockito.eq(professor), Mockito.eq(ProfessorSaidaDto.class))).thenReturn(new ProfessorSaidaDto());

        List<ProfessorSaidaDto> professor1 = professorMapper.parseEntityListToOutputList(professor);

        Assertions.assertNotNull(professor1);
        Assertions.assertEquals(professor.size(), professor1.size());
    }

    @Test
    public void parseLocalDate(){

        ProfessorEntradaDto professor = Mockito.mock(ProfessorEntradaDto.class, Answers.RETURNS_MOCKS);

        professorMapper.parseStringToLocalDate(professor.getContratacao());

    }

}
