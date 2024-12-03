package br.grupointegrado.educacional.repository;

import br.grupointegrado.educacional.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
}
