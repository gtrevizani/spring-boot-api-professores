package br.com.start.domain.service;

import br.com.start.api.assembler.ProfessorMapper;
import br.com.start.domain.enums.TabelaDeErros;
import br.com.start.domain.model.Professor;
import br.com.start.api.dto.ProfessorSaidaDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.start.domain.exception.ErroDeNegocioException;
import br.com.start.api.dto.ProfessorEntradaDto;
import br.com.start.domain.repository.ProfessorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ProfessorService.class)
@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class ProfessorServiceTest {
	@MockBean
	private ProfessorMapper mapper;
	@MockBean
	private ProfessorRepository repository;
	@InjectMocks
	private ProfessorService service;


	@BeforeEach
	public void setup(){
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void criar(){
		ProfessorEntradaDto professor = Mockito.mock(ProfessorEntradaDto.class, Answers.RETURNS_MOCKS);




		assertDoesNotThrow(() -> service.criar(professor));


	}
	@Test
	void criar_QuandoDadosInvalidos() {
//		ProfessorEntradaDto professor = Mockito.mock(ProfessorEntradaDto.class, Answers.RETURNS_MOCKS);
//
//
//		ErroDeNegocioException e =
//				Assertions.assertThrows(ErroDeNegocioException.class,
//						()-> service.criar(professor));
//
//
//
//		TabelaDeErros tabela = TabelaDeErros.ERRO_VALIDACAO;
//		Assertions.assertEquals(tabela.getErro(), e.getMessage());
	}

	@Test
	void criar_QuandoEmailCadastrado() {

		ProfessorEntradaDto professor = Mockito.mock(ProfessorEntradaDto.class, Answers.RETURNS_MOCKS);


		when(repository.existsByEmail(Mockito.any(String.class))).thenReturn(true);

		ErroDeNegocioException e =
				Assertions.assertThrows(ErroDeNegocioException.class,
						()-> service.criar(professor));

		TabelaDeErros tabela = TabelaDeErros.EMAIL_JA_CADASTRADO;
		Assertions.assertEquals(tabela.getErro(), e.getErro());
	}

	@Test
	void alterar(){
		ProfessorEntradaDto professor = Mockito.mock(ProfessorEntradaDto.class, Answers.RETURNS_MOCKS);
		Professor professorWithId = Mockito.mock(Professor.class, Answers.RETURNS_MOCKS);

		given(repository.findById(anyLong())).willReturn(Optional.of(professorWithId));
		given(repository.save(professorWithId)).willReturn(professorWithId);

		assertDoesNotThrow(() -> service.alterar(professorWithId.getId(), professor));


	}

	@Test
	void alterar_QuandoIdNãoCadastrado(){

		ProfessorEntradaDto professor = Mockito.mock(ProfessorEntradaDto.class, Answers.RETURNS_MOCKS);
		Professor professorWithId = Mockito.mock(Professor.class, Answers.RETURNS_MOCKS);

		given(repository.findById(anyLong())).willReturn(Optional.empty());
		given(repository.save(professorWithId)).willReturn(professorWithId);

		ErroDeNegocioException e =
				Assertions.assertThrows(ErroDeNegocioException.class,
						()-> service.alterar(professorWithId.getId(), professor));

		TabelaDeErros tabela = TabelaDeErros.PROFESSOR_NAO_ENCONTRADO;
		Assertions.assertEquals(tabela.getErro(), e.getErro());


	}

	@Test
	void alterar_QuandoEmailCadastrado(){

		ProfessorEntradaDto professor = Mockito.mock(ProfessorEntradaDto.class, Answers.RETURNS_MOCKS);
		Professor professorWithId = Mockito.mock(Professor.class, Answers.RETURNS_MOCKS);

		given(repository.findById(anyLong())).willReturn(Optional.of(professorWithId));
		given(repository.existsByEmail(any())).willReturn(true);
		given(repository.save(professorWithId)).willReturn(professorWithId);

		ErroDeNegocioException e =
				Assertions.assertThrows(ErroDeNegocioException.class,
						()-> service.alterar(professorWithId.getId(), professor));

		TabelaDeErros tabela = TabelaDeErros.EMAIL_JA_CADASTRADO;
		Assertions.assertEquals(tabela.getErro(), e.getErro());


	}

	@Test
	void consultar(){
		Professor professorWithId = Mockito.mock(Professor.class, Answers.RETURNS_MOCKS);


		when(repository.findById(professorWithId.getId())).thenReturn(Optional.of(professorWithId));


		assertDoesNotThrow(() -> service.consultar(professorWithId.getId()));
		assertFalse(repository.findById(professorWithId.getId()).isEmpty());
		assertTrue(repository.findById(professorWithId.getId()).isPresent());
		assertEquals(Optional.of(professorWithId), repository.findById(professorWithId.getId()));
		verify(repository, times(4)).findById(professorWithId.getId());
	}

	@Test
	void consultar_QuandoIdNaoExistir(){
		Professor professorWithId = Mockito.mock(Professor.class, Answers.RETURNS_MOCKS);

		when(repository.findById(professorWithId.getId())).thenReturn(Optional.empty());


		ErroDeNegocioException e =
				Assertions.assertThrows(ErroDeNegocioException.class,
						()-> service.consultar(professorWithId.getId()));

		TabelaDeErros tabela = TabelaDeErros.PROFESSOR_NAO_ENCONTRADO;
		Assertions.assertEquals(tabela.getErro(), e.getErro());


	}

	@Test
	void excluir(){
		//Given
		Professor professorWithId = Mockito.mock(Professor.class, Answers.RETURNS_MOCKS);

		//When
		when(repository.existsById(professorWithId.getId())).thenReturn(true);


		//Assert
		assertDoesNotThrow(() -> service.excluir(professorWithId.getId()));
		verify(repository, times(1)).existsById(professorWithId.getId());
		verify(repository, times(1)).deleteById(professorWithId.getId());
	}

	@Test
	void excluir_QuandoIdNaoExistir(){

		Professor professorWithId = Mockito.mock(Professor.class, Answers.RETURNS_MOCKS);

		//When
		when(repository.findById(professorWithId.getId())).thenReturn(Optional.empty());


		//Assert
		ErroDeNegocioException e =
				Assertions.assertThrows(ErroDeNegocioException.class,
						()-> service.excluir(professorWithId.getId()));

		TabelaDeErros tabela = TabelaDeErros.SEM_DADOS;
		Assertions.assertEquals(tabela.getErro(), e.getErro());

	}

	@Test
	void listar(){
		//Given
		Professor professorWithId1 = Mockito.mock(Professor.class, Answers.RETURNS_MOCKS);
		Professor professorWithId2 = Mockito.mock(Professor.class, Answers.RETURNS_MOCKS);
		List<Professor> professorList = new ArrayList<>();
		professorList.add(professorWithId1);
		professorList.add(professorWithId2);

		//When
		when(repository.findAll()).thenReturn(professorList);
		when(mapper.parseProfessorList(professorList)).then(invocation -> professorList);
		List<ProfessorSaidaDto> resultList = assertDoesNotThrow(() -> service.listar());

		//Then
		Assertions.assertEquals(2, resultList.size());
		Assertions.assertEquals(2, professorList.size());
		verify(repository, times(1)).findAll();
		verify(mapper, times(1)).parseProfessorList(professorList);
		verifyNoMoreInteractions(repository, mapper);
	}

	@Test
	void listar_QuandoListaVazio(){

		List<Professor> professorList = new ArrayList<>();

		//When
		when(repository.findAll()).thenReturn(professorList);
		when(mapper.parseProfessorList(professorList)).then(invocation -> professorList);

		ErroDeNegocioException e =
				Assertions.assertThrows(ErroDeNegocioException.class,
						()-> service.listar());

		TabelaDeErros tabela = TabelaDeErros.SEM_DADOS;
		Assertions.assertEquals(tabela.getErro(), e.getErro());
		Assertions.assertEquals(0, professorList.size());



	}

}
