package com.engjaconi.agendamento_notificacao_api.business.mapper;

import com.engjaconi.agendamento_notificacao_api.controller.dto.in.AgendamentoRecordInDto;
import com.engjaconi.agendamento_notificacao_api.controller.dto.out.AgendamentoRecordOutDto;
import com.engjaconi.agendamento_notificacao_api.infrastructure.entities.Agendamento;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgendamentoMapper {

    Agendamento paraEntity(AgendamentoRecordInDto agendamentoRecordInDto);

    AgendamentoRecordOutDto paraOutDto(Agendamento agendamento);

}
