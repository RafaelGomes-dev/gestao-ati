package com.Rafael.gestao_ati.dto;

import lombok.Data;
import java.time.LocalDateTime;


@Data
public class FuncionarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String departamento;
    private String cargo;
    private Boolean ativo;
    private LocalDateTime criadoEm;

}
