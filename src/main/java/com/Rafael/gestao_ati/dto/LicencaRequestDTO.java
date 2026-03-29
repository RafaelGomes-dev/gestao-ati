package com.Rafael.gestao_ati.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDate;

@Data
public class LicencaRequestDTO {

    @NotBlank(message = "Software é obrigatório")
    private String software;

    private String chave;
    private LocalDate dataExpiracao;

    @NotBlank(message = "Tipo é obrigatório")
    private String tipo;

    private Long funcionarioId;
}