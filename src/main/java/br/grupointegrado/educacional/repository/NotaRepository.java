package br.grupointegrado.educacional.repository;

import br.grupointegrado.educacional.model.Disciplina;
import br.grupointegrado.educacional.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Integer> {
    List<Nota> findByDisciplina(Disciplina disciplina);
}
