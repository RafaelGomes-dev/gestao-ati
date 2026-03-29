package com.Rafael.gestao_ati.service;

import com.Rafael.gestao_ati.dto.FuncionarioRequestDTO;
import com.Rafael.gestao_ati.dto.FuncionarioResponseDTO;
import com.Rafael.gestao_ati.model.Funcionario;
import com.Rafael.gestao_ati.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository repository;
    private final AuditoriaService auditoriaService;

    public FuncionarioResponseDTO criar(FuncionarioRequestDTO dto) {
        if (repository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.getNome());
        funcionario.setEmail(dto.getEmail());
        funcionario.setDepartamento(dto.getDepartamento());
        funcionario.setCargo(dto.getCargo());
        FuncionarioResponseDTO response = toDTO(repository.save(funcionario));
        auditoriaService.registrar("Funcionario", response.getId(), "CRIAR",
                "Funcionário criado: " + dto.getNome());
        return response;
    }

    public List<FuncionarioResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public FuncionarioResponseDTO buscarPorId(Long id) {
        Funcionario funcionario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        return toDTO(funcionario);
    }

    public FuncionarioResponseDTO atualizar(Long id, FuncionarioRequestDTO dto) {
        Funcionario funcionario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        funcionario.setNome(dto.getNome());
        funcionario.setEmail(dto.getEmail());
        funcionario.setDepartamento(dto.getDepartamento());
        funcionario.setCargo(dto.getCargo());
        FuncionarioResponseDTO response = toDTO(repository.save(funcionario));
        auditoriaService.registrar("Funcionario", id, "ATUALIZAR",
                "Funcionário atualizado: " + dto.getNome());
        return response;
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Funcionário não encontrado");
        }
        auditoriaService.registrar("Funcionario", id, "DELETAR",
                "Funcionário deletado com id: " + id);
        repository.deleteById(id);
    }

    private FuncionarioResponseDTO toDTO(Funcionario funcionario) {
        FuncionarioResponseDTO dto = new FuncionarioResponseDTO();
        dto.setId(funcionario.getId());
        dto.setNome(funcionario.getNome());
        dto.setEmail(funcionario.getEmail());
        dto.setDepartamento(funcionario.getDepartamento());
        dto.setCargo(funcionario.getCargo());
        dto.setAtivo(funcionario.getAtivo());
        dto.setCriadoEm(funcionario.getCriadoEm());
        return dto;
    }
}