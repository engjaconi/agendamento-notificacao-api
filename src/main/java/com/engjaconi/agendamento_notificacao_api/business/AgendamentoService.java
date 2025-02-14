package com.engjaconi.agendamento_notificacao_api.business;

import com.engjaconi.agendamento_notificacao_api.business.mapper.AgendamentoMapper;
import com.engjaconi.agendamento_notificacao_api.controller.dto.in.AgendamentoRecordInDto;
import com.engjaconi.agendamento_notificacao_api.controller.dto.out.AgendamentoRecordOutDto;
import com.engjaconi.agendamento_notificacao_api.infrastructure.repositories.AgendamentoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final AgendamentoMapper agendamentoMapper;

    public AgendamentoRecordOutDto salvarAgendamento(AgendamentoRecordInDto agendamentoRecordInDto) {
        return agendamentoMapper.paraOutDto(
                agendamentoRepository.save(
                        agendamentoMapper.paraEntity(agendamentoRecordInDto)
                )
        );
    }

}
