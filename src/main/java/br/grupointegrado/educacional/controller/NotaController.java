package br.grupointegrado.educacional.controller;

import br.grupointegrado.educacional.dto.NotaRequestDTO;
import br.grupointegrado.educacional.model.Disciplina;
import br.grupointegrado.educacional.model.Matricula;
import br.grupointegrado.educacional.model.Nota;
import br.grupointegrado.educacional.repository.DisciplinaRepository;
import br.grupointegrado.educacional.repository.MatriculaRepository;
import br.grupointegrado.educacional.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")
public class NotaController {

    @Autowired
    NotaRepository NotaRepository;

    @Autowired
    MatriculaRepository matriculaRepository;

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @GetMapping
    public ResponseEntity<List<Nota>> finAll() {
        List<Nota> notas =  this.NotaRepository.findAll();
        return ResponseEntity.ok(notas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nota> findById(@PathVariable Integer id) {
        Nota nota = this.NotaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada."));
        return ResponseEntity.ok(nota);
    }

    @PostMapping
    public ResponseEntity<Nota> save(@RequestBody NotaRequestDTO dto) {
        Nota nota = new Nota();

        nota.setNota(dto.nota());
        nota.setData_lancamento(dto.data_lancamento());

        Matricula matricula = this.matriculaRepository.findById(dto.matricula_id())
                .orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada."));

        nota.setMatricula(matricula);

        Disciplina disciplina = this.disciplinaRepository.findById(dto.disciplina_id())
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada."));

        nota.setDisciplina(disciplina);

        Nota savedNota = this.NotaRepository.save(nota);

        return ResponseEntity.ok(savedNota);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nota> update(@PathVariable Integer id, @RequestBody NotaRequestDTO dto) {
        Nota nota = this.NotaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada."));

        nota.setNota(dto.nota());
        nota.setData_lancamento(dto.data_lancamento());

        Matricula matricula = this.matriculaRepository.findById(dto.matricula_id())
                .orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada."));

        nota.setMatricula(matricula);

        Disciplina disciplina = this.disciplinaRepository.findById(dto.disciplina_id())
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada."));

        nota.setDisciplina(disciplina);

        Nota savedNota = this.NotaRepository.save(nota);

        return ResponseEntity.ok(savedNota);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Nota nota = this.NotaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada."));

        this.NotaRepository.delete(nota);
        return ResponseEntity.noContent().build();
    }

}
