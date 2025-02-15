package com.engjaconi.agendamento_notificacao_api.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.engjaconi.agendamento_notificacao_api.business.AgendamentoService;
import com.engjaconi.agendamento_notificacao_api.controller.dto.in.AgendamentoRecordInDto;
import com.engjaconi.agendamento_notificacao_api.controller.dto.out.AgendamentoRecordOutDto;
import com.engjaconi.agendamento_notificacao_api.infrastructure.enums.StatusNotificacaoEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ExtendWith(MockitoExtension.class)
class AgendamentoControllerTest {

    @InjectMocks
    private AgendamentoController agendamentoController;

    @Mock
    private AgendamentoService agendamentoService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    private AgendamentoRecordInDto agendamentoRecordInDto;
    private AgendamentoRecordOutDto agendamentoRecordOutDto;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(agendamentoController).build();
        objectMapper.registerModule(new JavaTimeModule());

        agendamentoRecordInDto = new AgendamentoRecordInDto(
            "email@email.com", "11922223333",
            "Mensagem de teste", LocalDateTime.of(2025, 1, 1, 10, 1, 1)
        );

        agendamentoRecordOutDto = new AgendamentoRecordOutDto(
            1L, "email@email.com", "11922223333", "Mensagem de teste", 
            LocalDateTime.of(2025, 1, 1, 10, 1, 1), StatusNotificacaoEnum.AGENDADO
        );
    }

    @Test
    void deveSalvarAgendamentoComSucesso() throws Exception {
        when(agendamentoService.salvarAgendamento(agendamentoRecordInDto)).thenReturn(agendamentoRecordOutDto);
        
        mockMvc.perform(post("/agendamento")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsBytes(agendamentoRecordInDto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.emailDestinatario").value("email@email.com"))
        .andExpect(jsonPath("$.telefoneDestinatario").value(agendamentoRecordOutDto.telefoneDestinatario()))
        .andExpect(jsonPath("$.mensagem").value(agendamentoRecordOutDto.mensagem()))
        .andExpect(jsonPath("$.dataHoraEnvio").value("01-01-2025 10:01:01"))
        .andExpect(jsonPath("$.statusNotificacao").value("AGENDADO"));

        verify(agendamentoService, times(1)).salvarAgendamento(agendamentoRecordInDto);
    }

    @Test
    void deveBuscarAgendamentoComSucesso() throws Exception {
        when(agendamentoService.buscarAgendamentoPorId(1L)).thenReturn(agendamentoRecordOutDto);
        
        mockMvc.perform(get("/agendamento/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.emailDestinatario").value("email@email.com"))
        .andExpect(jsonPath("$.telefoneDestinatario").value(agendamentoRecordOutDto.telefoneDestinatario()))
        .andExpect(jsonPath("$.mensagem").value(agendamentoRecordOutDto.mensagem()))
        .andExpect(jsonPath("$.dataHoraEnvio").value("01-01-2025 10:01:01"))
        .andExpect(jsonPath("$.statusNotificacao").value("AGENDADO"));

        verify(agendamentoService, times(1)).buscarAgendamentoPorId(1L);
    }

    @Test
    void deveCancelarAgendamentoComSucesso() throws Exception {
        doNothing().when(agendamentoService).cancelarAgendamento(1L);
        
        mockMvc.perform(delete("/agendamento/1")).andExpect(status().isAccepted());

        verify(agendamentoService, times(1)).cancelarAgendamento(1L);
    }

}
