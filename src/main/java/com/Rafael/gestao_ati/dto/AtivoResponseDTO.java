package com.Rafael.gestao_ati.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AtivoResponseDTO {
    private Long id;
    private String nome;
    private String tipo;
    private String numeroSerie;
    private String status;
    private String descricao;
    private LocalDateTime criadoEm;
    private String funcionarioNome;
}