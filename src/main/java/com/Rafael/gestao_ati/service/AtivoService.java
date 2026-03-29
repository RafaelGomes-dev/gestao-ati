package com.Rafael.gestao_ati.service;

import com.Rafael.gestao_ati.dto.AtivoRequestDTO;
import com.Rafael.gestao_ati.dto.AtivoResponseDTO;
import com.Rafael.gestao_ati.model.Ativo;
import com.Rafael.gestao_ati.model.Funcionario;
import com.Rafael.gestao_ati.repository.AtivoRepository;
import com.Rafael.gestao_ati.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AtivoService {

    private final AtivoRepository repository;
    private final FuncionarioRepository funcionarioRepository;

    public AtivoResponseDTO criar(AtivoRequestDTO dto) {
        if (dto.getNumeroSerie() != null && repository.existsByNumeroSerie(dto.getNumeroSerie())) {
            throw new RuntimeException("Número de série já cadastrado");
        }
        Ativo ativo = new Ativo();
        ativo.setNome(dto.getNome());
        ativo.setTipo(dto.getTipo());
        ativo.setNumeroSerie(dto.getNumeroSerie());
        ativo.setStatus(dto.getStatus());
        ativo.setDescricao(dto.getDescricao());
        if (dto.getFuncionarioId() != null) {
            Funcionario funcionario = funcionarioRepository.findById(dto.getFuncionarioId())
                    .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
            ativo.setFuncionario(funcionario);
        }
        return toDTO(repository.save(ativo));
    }

    public List<AtivoResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AtivoResponseDTO buscarPorId(Long id) {
        return toDTO(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ativo não encontrado")));
    }

    public AtivoResponseDTO atualizar(Long id, AtivoRequestDTO dto) {
        Ativo ativo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ativo não encontrado"));
        ativo.setNome(dto.getNome());
        ativo.setTipo(dto.getTipo());
        ativo.setNumeroSerie(dto.getNumeroSerie());
        ativo.setStatus(dto.getStatus());
        ativo.setDescricao(dto.getDescricao());
        if (dto.getFuncionarioId() != null) {
            Funcionario funcionario = funcionarioRepository.findById(dto.getFuncionarioId())
                    .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
            ativo.setFuncionario(funcionario);
        }
        return toDTO(repository.save(ativo));
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Ativo não encontrado");
        }
        repository.deleteById(id);
    }

    private AtivoResponseDTO toDTO(Ativo ativo) {
        AtivoResponseDTO dto = new AtivoResponseDTO();
        dto.setId(ativo.getId());
        dto.setNome(ativo.getNome());
        dto.setTipo(ativo.getTipo());
        dto.setNumeroSerie(ativo.getNumeroSerie());
        dto.setStatus(ativo.getStatus());
        dto.setDescricao(ativo.getDescricao());
        dto.setCriadoEm(ativo.getCriadoEm());
        if (ativo.getFuncionario() != null) {
            dto.setFuncionarioNome(ativo.getFuncionario().getNome());
        }
        return dto;
    }
}