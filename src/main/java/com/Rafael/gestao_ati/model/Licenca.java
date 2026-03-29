package com.Rafael.gestao_ati.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java .time.LocalDateTime;

@Data
@Entity
@Table(name = "licencas")
public class Licenca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String software;

    @Column(unique = true)
    private String chave;

    @Column(name = "data_expiracao")
    private LocalDate dataExpiracao;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @PrePersist
    public void prePersist() {
        this.criadoEm = LocalDateTime.now();
    }
}
