package com.Rafael.gestao_ati.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FuncionarioRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Email é obrigatorio")
    @Email(message = "Email inválido")
    private String email;

    private String departamento;
    private String cargo;

}
