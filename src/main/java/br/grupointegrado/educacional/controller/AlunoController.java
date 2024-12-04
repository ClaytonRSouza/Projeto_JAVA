package br.grupointegrado.educacional.controller;

import br.grupointegrado.educacional.dto.AlunoRequestDTO;
import br.grupointegrado.educacional.dto.BoletimResponseDTO;
import br.grupointegrado.educacional.dto.DisciplinaResponseDTO;
import br.grupointegrado.educacional.dto.NotaResponseDTO;
import br.grupointegrado.educacional.model.Aluno;
import br.grupointegrado.educacional.model.Matricula;
import br.grupointegrado.educacional.model.Nota;
import br.grupointegrado.educacional.repository.AlunoRepository;
import br.grupointegrado.educacional.repository.MatriculaRepository;
import br.grupointegrado.educacional.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository AlunoRepository;

    @Autowired
    private MatriculaRepository MatriculaRepository;

    @Autowired
    private TurmaRepository TurmaRepository;

    @GetMapping
    public List<Aluno> findAll() {
        return this.AlunoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Aluno findById(@PathVariable Integer id){
        return this.AlunoRepository.findById(id)
                .orElseThrow((() -> new IllegalArgumentException("Aluno não encontrado")));
    }

    @PostMapping
    public Aluno save(@RequestBody AlunoRequestDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setMatricula(dto.matricula());
        aluno.setTelefone(dto.telefone());
        aluno.setEspecialidade(dto.especialidade());

        return this.AlunoRepository.save(aluno);
    }

    @PutMapping("/{id}")
    public Aluno update(@PathVariable Integer id,
                        @RequestBody AlunoRequestDTO dto) {
        Aluno aluno = this.AlunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setMatricula(dto.matricula());
        aluno.setTelefone(dto.telefone());
        aluno.setEspecialidade(dto.especialidade());

        return this.AlunoRepository.save(aluno);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Aluno aluno = this.AlunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
        this.AlunoRepository.delete(aluno);
    }

    @GetMapping("/{aluno_id}/boletim")
    public ResponseEntity<BoletimResponseDTO> getNotas(@PathVariable Integer aluno_id) {
        Aluno aluno = this.AlunoRepository.findById(aluno_id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        List<NotaResponseDTO> notas = new ArrayList<>();
        if (!aluno.getMatriculas().isEmpty()) {
            for (Matricula matricula : aluno.getMatriculas()) {
                for (Nota nota : matricula.getNotas()) {
                    notas.add(
                            new NotaResponseDTO(
                                    nota.getNota(),
                                    nota.getData_lancamento(),
                                    new DisciplinaResponseDTO(
                                            nota.getDisciplina().getNome(),
                                            nota.getDisciplina().getCodigo()
                                    )
                            )
                    );
                }
            }
        }
        return ResponseEntity.ok(new BoletimResponseDTO(notas));
    }

}
