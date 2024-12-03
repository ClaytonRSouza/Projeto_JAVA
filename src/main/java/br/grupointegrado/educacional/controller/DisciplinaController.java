package br.grupointegrado.educacional.controller;


import br.grupointegrado.educacional.dto.DisciplinaRequestDTO;
import br.grupointegrado.educacional.model.Curso;
import br.grupointegrado.educacional.model.Disciplina;
import br.grupointegrado.educacional.model.Professor;
import br.grupointegrado.educacional.repository.CursoRepository;
import br.grupointegrado.educacional.repository.DisciplinaRepository;
import br.grupointegrado.educacional.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository DisciplinaRepository;

    @Autowired
    private CursoRepository CursoRepository;

    @Autowired
    private ProfessorRepository ProfessorRepository;

    @GetMapping
    public ResponseEntity<List<Disciplina>> findAll() {
        List<Disciplina> disciplinas = this.DisciplinaRepository.findAll();
        return ResponseEntity.ok(disciplinas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> findById(@PathVariable Integer id){
        Disciplina disciplina = this.DisciplinaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        return ResponseEntity.ok(disciplina);
    }

    @PostMapping
    public ResponseEntity<Disciplina> save(@RequestBody DisciplinaRequestDTO dto) {
        Disciplina disciplina = new Disciplina();

        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());

        Curso curso = this.CursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        disciplina.setCurso(curso);

        Professor professor = this.ProfessorRepository.findById(dto.professor_id())
                .orElseThrow(() -> new IllegalArgumentException("PRofessor não encontrado"));

        disciplina.setProfessor(professor);

        Disciplina savedDisciplina = DisciplinaRepository.save(disciplina);
        return ResponseEntity.ok(savedDisciplina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> update(@PathVariable Integer id, @RequestBody DisciplinaRequestDTO dto) {
        Disciplina disciplina = this.DisciplinaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());

        Curso curso = this.CursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        disciplina.setCurso(curso);

        Professor professor = this.ProfessorRepository.findById(dto.professor_id())
                .orElseThrow(() -> new IllegalArgumentException("PRofessor não encontrado"));

        disciplina.setProfessor(professor);

        Disciplina savedDisciplina = DisciplinaRepository.save(disciplina);
        return ResponseEntity.ok(savedDisciplina);
    }

    @DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Disciplina disciplina = this.DisciplinaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        this.DisciplinaRepository.delete(disciplina);
        return ResponseEntity.noContent().build();
    }
}
