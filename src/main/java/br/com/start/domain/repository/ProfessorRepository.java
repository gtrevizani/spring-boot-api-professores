package br.com.start.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.start.domain.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>{

	boolean existsByEmail(String email);



}
