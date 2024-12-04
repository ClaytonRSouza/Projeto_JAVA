package br.grupointegrado.educacional.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NotaResponseDTO(BigDecimal nota, LocalDate data_lancamento, DisciplinaResponseDTO disciplina) {
}
