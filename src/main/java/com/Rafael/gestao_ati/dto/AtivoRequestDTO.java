package com.Rafael.gestao_ati.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AtivoRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Tipo é obrigatório")
    private String tipo;

    private String numeroSerie;

    @NotBlank(message = "Status é obrigatório")
    private String status;

    private String descricao;
    private Long funcionarioId;
}