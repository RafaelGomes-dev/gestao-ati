package com.Rafael.gestao_ati.repository;

import com.Rafael.gestao_ati.model.LogAuditoria;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LogAuditoriaRepository extends JpaRepository<LogAuditoria, Long> {

    List<LogAuditoria> findByEntidade(String entidade);
    List<LogAuditoria> findByUsuarioEmail(String usuarioEmail);

}
