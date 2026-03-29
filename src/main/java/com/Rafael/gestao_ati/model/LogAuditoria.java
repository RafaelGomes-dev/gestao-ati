package com.Rafael.gestao_ati.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "logs_auditoria")
public class LogAuditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String entidade;

    @Column(name = "entidade_id")
    private Long entidadeId;

    @Column(nullable = false)
    private String acao;

    @Column(name = "usuario_email")
    private String usuarioEmail;

    @Column(name = "realizado_em", updatable = false)
    private LocalDateTime realizadoEm;

    private String detalhes;

    @PrePersist
    public void prePersist() {
        this.realizadoEm = LocalDateTime.now();
    }

}
