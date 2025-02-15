package com.engjaconi.agendamento_notificacao_api.business;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.engjaconi.agendamento_notificacao_api.business.mapper.AgendamentoMapper;
import com.engjaconi.agendamento_notificacao_api.controller.dto.in.AgendamentoRecordInDto;
import com.engjaconi.agendamento_notificacao_api.controller.dto.out.AgendamentoRecordOutDto;
import com.engjaconi.agendamento_notificacao_api.infrastructure.entities.Agendamento;
import com.engjaconi.agendamento_notificacao_api.infrastructure.enums.StatusNotificacaoEnum;
import com.engjaconi.agendamento_notificacao_api.infrastructure.repositories.AgendamentoRepository;

@ExtendWith(MockitoExtension.class)
class AgendamentoServiceTest {

    @InjectMocks
    private AgendamentoService agendamentoService;

    @Mock
    private AgendamentoRepository agendamentoRepository;
    @Mock
    private AgendamentoMapper agendamentoMapper;

    private AgendamentoRecordInDto agendamentoRecordInDto;
    private AgendamentoRecordOutDto agendamentoRecordOutDto;
    private Agendamento agendamentoEntity;
    private Agendamento agendamentoCanceladoEntity;

    @BeforeEach
    void setUp() {

        agendamentoEntity = Agendamento.builder()
            .id(1L)
            .emailDestinatario("email@email.com")
            .telefoneDestinatario("11922223333")
            .mensagem("Mensagem de teste")
            .dataHoraEnvio(LocalDateTime.of(2025, 1, 1, 10, 1, 1))
            .dataHoraAgendamento(LocalDateTime.of(2025, 1, 1, 10, 1, 1))
            .statusNotificacao(StatusNotificacaoEnum.AGENDADO)
            .build();

        agendamentoRecordInDto = new AgendamentoRecordInDto(
            "email@email.com", "11922223333",
            "Mensagem de teste", LocalDateTime.of(2025, 1, 1, 10, 1, 1)
        );

        agendamentoRecordOutDto = new AgendamentoRecordOutDto(
            1L, "email@email.com", "11922223333", "Mensagem de teste", 
            LocalDateTime.of(2025, 1, 1, 10, 1, 1), StatusNotificacaoEnum.AGENDADO
        );

        agendamentoCanceladoEntity = Agendamento.builder()
            .id(1L)
            .emailDestinatario("email@email.com")
            .telefoneDestinatario("11922223333")
            .mensagem("Mensagem de teste")
            .dataHoraEnvio(LocalDateTime.of(2025, 1, 1, 10, 1, 1))
            .dataHoraAgendamento(LocalDateTime.of(2025, 1, 1, 10, 1, 1))
            .statusNotificacao(StatusNotificacaoEnum.CANCELADO)
            .build();
    }

    @Test
    void deveSalvarAgendamentoComSucesso() {
        when(agendamentoMapper.paraEntity(agendamentoRecordInDto)).thenReturn(agendamentoEntity);
        when(agendamentoRepository.save(agendamentoEntity)).thenReturn(agendamentoEntity);
        when(agendamentoMapper.paraOutDto(agendamentoEntity)).thenReturn(agendamentoRecordOutDto);

        AgendamentoRecordOutDto out = agendamentoService.salvarAgendamento(agendamentoRecordInDto);

        verify(agendamentoMapper, times(1)).paraEntity(agendamentoRecordInDto);
        verify(agendamentoRepository, times(1)).save(agendamentoEntity);
        verify(agendamentoMapper, times(1)).paraOutDto(agendamentoEntity);

        assertThat(out).usingRecursiveComparison().isEqualTo(agendamentoRecordOutDto);
    }

    @Test
    void deveBuscarAgendamentoComSucesso() {
        when(agendamentoRepository.findById(1L)).thenReturn(Optional.of(agendamentoEntity));
        when(agendamentoMapper.paraOutDto(agendamentoEntity)).thenReturn(agendamentoRecordOutDto);

        AgendamentoRecordOutDto out = agendamentoService.buscarAgendamentoPorId(1L);

        verify(agendamentoRepository, times(1)).findById(1L);
        verify(agendamentoMapper, times(1)).paraOutDto(agendamentoEntity);

        assertThat(out).usingRecursiveComparison().isEqualTo(agendamentoRecordOutDto);
    }

    @Test
    void deveCancelarAgendamentoComSucesso() {
        when(agendamentoRepository.findById(1L)).thenReturn(Optional.of(agendamentoEntity));
        when(agendamentoMapper.paraEntityCancelamento(agendamentoEntity)).thenReturn(agendamentoCanceladoEntity);
        when(agendamentoRepository.save(agendamentoCanceladoEntity)).thenReturn(agendamentoCanceladoEntity);

        agendamentoService.cancelarAgendamento(1L);

        verify(agendamentoRepository, times(1)).findById(1L);
        verify(agendamentoMapper, times(1)).paraEntityCancelamento(agendamentoEntity);
        verify(agendamentoRepository, times(1)).save(agendamentoCanceladoEntity);
    }

}
