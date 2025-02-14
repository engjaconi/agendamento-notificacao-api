package com.engjaconi.agendamento_notificacao_api.infrastructure.repositories;

import com.engjaconi.agendamento_notificacao_api.infrastructure.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
