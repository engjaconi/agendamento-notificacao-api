package com.engjaconi.agendamento_notificacao_api.business.mapper;

import com.engjaconi.agendamento_notificacao_api.controller.dto.in.AgendamentoRecordInDto;
import com.engjaconi.agendamento_notificacao_api.controller.dto.out.AgendamentoRecordOutDto;
import com.engjaconi.agendamento_notificacao_api.infrastructure.entities.Agendamento;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgendamentoMapper {

    Agendamento paraEntity(AgendamentoRecordInDto agendamentoRecordInDto);

    AgendamentoRecordOutDto paraOutDto(Agendamento agendamento);

    @Mapping(target = "dataHoraModificacao", expression = "java(LocalDateTime.now())")
    @Mapping(target = "statusNotificacao", expression = "java(StatusNotificacaoEnum.CANCELADO)")
    Agendamento paraEntityCancelamento(Agendamento agendamento);

}
