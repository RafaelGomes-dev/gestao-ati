package com.Rafael.gestao_ati.service;

import com.Rafael.gestao_ati.model.LogAuditoria;
import com.Rafael.gestao_ati.repository.LogAuditoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditoriaService {

    private final LogAuditoriaRepository repository;

    public void registrar(String entidade, Long entidadeId, String acao, String detalhes) {
        String email = "Sistema";
        try {
            var auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.getPrincipal() != null) {
                email = auth.getPrincipal().toString();
            }
        } catch (Exception ignored) {}

        LogAuditoria log = new LogAuditoria();
        log.setEntidade(entidade);
        log.setEntidadeId(entidadeId);
        log.setAcao(acao);
        log.setUsuarioEmail(email);
        log.setDetalhes(detalhes);
        repository.save(log);
    }

    public List<LogAuditoria>listarTodos() {
        return repository.findAll();
    }

    public List<LogAuditoria>listarPorEntidade(String entidade) {
        return repository.findByEntidade(entidade);
    }

    public List<LogAuditoria>listarPorUsuario(String email) {
        return repository.findByUsuarioEmail(email);
    }

}
