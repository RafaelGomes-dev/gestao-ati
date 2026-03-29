package com.Rafael.gestao_ati.service;

import com.Rafael.gestao_ati.dto.LicencaRequestDTO;
import com.Rafael.gestao_ati.dto.LicencaResponseDTO;
import com.Rafael.gestao_ati.model.Funcionario;
import com.Rafael.gestao_ati.model.Licenca;
import com.Rafael.gestao_ati.repository.FuncionarioRepository;
import com.Rafael.gestao_ati.repository.LicencaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LicencaService {

    private final LicencaRepository repository;
    private final FuncionarioRepository funcionarioRepository;

    public LicencaResponseDTO criar(LicencaRequestDTO dto) {
        if (dto.getChave() != null && repository.existsByChave(dto.getChave())) {
            throw new RuntimeException("Chave já cadastrada");
        }
        Licenca licenca = new Licenca();
        licenca.setSoftware(dto.getSoftware());
        licenca.setChave(dto.getChave());
        licenca.setDataExpiracao(dto.getDataExpiracao());
        licenca.setTipo(dto.getTipo());
        if (dto.getFuncionarioId() != null) {
            Funcionario funcionario = funcionarioRepository.findById(dto.getFuncionarioId())
                    .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
            licenca.setFuncionario(funcionario);
        }
        return toDTO(repository.save(licenca));
    }

    public List<LicencaResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public LicencaResponseDTO buscarPorId(Long id) {
        return toDTO(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Licença não encontrada")));
    }

    public LicencaResponseDTO atualizar(Long id, LicencaRequestDTO dto) {
        Licenca licenca = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Licença não encontrada"));
        licenca.setSoftware(dto.getSoftware());
        licenca.setChave(dto.getChave());
        licenca.setDataExpiracao(dto.getDataExpiracao());
        licenca.setTipo(dto.getTipo());
        if (dto.getFuncionarioId() != null) {
            Funcionario funcionario = funcionarioRepository.findById(dto.getFuncionarioId())
                    .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
            licenca.setFuncionario(funcionario);
        }
        return toDTO(repository.save(licenca));
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Licença não encontrada");
        }
        repository.deleteById(id);
    }

    private LicencaResponseDTO toDTO(Licenca licenca) {
        LicencaResponseDTO dto = new LicencaResponseDTO();
        dto.setId(licenca.getId());
        dto.setSoftware(licenca.getSoftware());
        dto.setChave(licenca.getChave());
        dto.setDataExpiracao(licenca.getDataExpiracao());
        dto.setTipo(licenca.getTipo());
        dto.setAtivo(licenca.getAtivo());
        dto.setCriadoEm(licenca.getCriadoEm());
        if (licenca.getFuncionario() != null) {
            dto.setFuncionarioNome(licenca.getFuncionario().getNome());
        }
        return dto;
    }
}