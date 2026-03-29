package com.Rafael.gestao_ati.controller;

import com.Rafael.gestao_ati.model.LogAuditoria;
import com.Rafael.gestao_ati.service.AuditoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auditoria")
@RequiredArgsConstructor
public class AuditoriaController {

    private final AuditoriaService auditoriaService;

    @GetMapping
    public ResponseEntity<List<LogAuditoria>> listarTodos() {
        return ResponseEntity.ok(auditoriaService.listarTodos());
    }

    @GetMapping("/entidade/{entidade}")
    public ResponseEntity<List<LogAuditoria>> listarPorEntidade(@PathVariable String entidade) {
        return ResponseEntity.ok(auditoriaService.listarPorEntidade(entidade));
    }

    @GetMapping("/usuario/{email}")
    public ResponseEntity<List<LogAuditoria>> listarPorUsuario(@PathVariable String email) {
        return ResponseEntity.ok(auditoriaService.listarPorUsuario(email));
    }

}
