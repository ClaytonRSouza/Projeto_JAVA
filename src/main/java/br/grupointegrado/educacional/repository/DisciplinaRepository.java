package br.grupointegrado.educacional.repository;

import br.grupointegrado.educacional.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {
}
