package com.Rafael.gestao_ati.controller;

import com.Rafael.gestao_ati.dto.FuncionarioRequestDTO;
import com.Rafael.gestao_ati.dto.FuncionarioResponseDTO;
import com.Rafael.gestao_ati.service.FuncionarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService service;

    @PostMapping
    public ResponseEntity<EntityModel<FuncionarioResponseDTO>> criar(@Valid @RequestBody FuncionarioRequestDTO dto) {
        FuncionarioResponseDTO response = service.criar(dto);
        EntityModel<FuncionarioResponseDTO> model = EntityModel.of(response,
                linkTo(methodOn(FuncionarioController.class).buscarPorId(response.getId())).withSelfRel(),
                linkTo(methodOn(FuncionarioController.class).listarTodos()).withRel("todos"),
                linkTo(methodOn(FuncionarioController.class).deletar(response.getId())).withRel("deletar")
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<FuncionarioResponseDTO>>> listarTodos() {
        List<EntityModel<FuncionarioResponseDTO>> funcionarios = service.listarTodos()
                .stream()
                .map(f -> EntityModel.of(f,
                        linkTo(methodOn(FuncionarioController.class).buscarPorId(f.getId())).withSelfRel(),
                        linkTo(methodOn(FuncionarioController.class).deletar(f.getId())).withRel("deletar")
                ))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<FuncionarioResponseDTO>> collection = CollectionModel.of(funcionarios,
                linkTo(methodOn(FuncionarioController.class).listarTodos()).withSelfRel()
        );
        return ResponseEntity.ok(collection);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<FuncionarioResponseDTO>> buscarPorId(@PathVariable Long id) {
        FuncionarioResponseDTO response = service.buscarPorId(id);
        EntityModel<FuncionarioResponseDTO> model = EntityModel.of(response,
                linkTo(methodOn(FuncionarioController.class).buscarPorId(id)).withSelfRel(),
                linkTo(methodOn(FuncionarioController.class).listarTodos()).withRel("todos"),
                linkTo(methodOn(FuncionarioController.class).deletar(id)).withRel("deletar")
        );
        return ResponseEntity.ok(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<FuncionarioResponseDTO>> atualizar(@PathVariable Long id,
                                                                         @Valid @RequestBody FuncionarioRequestDTO dto) {
        FuncionarioResponseDTO response = service.atualizar(id, dto);
        EntityModel<FuncionarioResponseDTO> model = EntityModel.of(response,
                linkTo(methodOn(FuncionarioController.class).buscarPorId(id)).withSelfRel(),
                linkTo(methodOn(FuncionarioController.class).listarTodos()).withRel("todos")
        );
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}