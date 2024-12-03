package br.grupointegrado.educacional.dto;

import java.time.LocalDate;

public record NotaRequestDTO(Integer nota, LocalDate data_lancamento, Integer matricula_id, Integer disciplina_id) {
}
