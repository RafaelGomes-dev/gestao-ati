package com.Rafael.gestao_ati.controller;

import com.Rafael.gestao_ati.dto.LicencaRequestDTO;
import com.Rafael.gestao_ati.dto.LicencaResponseDTO;
import com.Rafael.gestao_ati.service.LicencaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/licencas")
@RequiredArgsConstructor
public class LicencaController {

    private final LicencaService service;

    @PostMapping
    public ResponseEntity<LicencaResponseDTO> criar(@Valid @RequestBody LicencaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<LicencaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LicencaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LicencaResponseDTO> atualizar(@PathVariable Long id,
                                                        @Valid @RequestBody LicencaRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}