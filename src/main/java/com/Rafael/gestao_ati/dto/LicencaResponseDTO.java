package com.Rafael.gestao_ati.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class LicencaResponseDTO {
    private Long id;
    private String software;
    private String chave;
    private LocalDate dataExpiracao;
    private String tipo;
    private Boolean ativo;
    private LocalDateTime criadoEm;
    private String funcionarioNome;
}