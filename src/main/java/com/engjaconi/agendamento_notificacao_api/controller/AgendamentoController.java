package com.engjaconi.agendamento_notificacao_api.controller;

import com.engjaconi.agendamento_notificacao_api.business.AgendamentoService;
import com.engjaconi.agendamento_notificacao_api.controller.dto.in.AgendamentoRecordInDto;
import com.engjaconi.agendamento_notificacao_api.controller.dto.out.AgendamentoRecordOutDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<AgendamentoRecordOutDto> salvarAgendamento(@RequestBody AgendamentoRecordInDto agendamentoRecordInDto) {
        return ResponseEntity.ok(agendamentoService.salvarAgendamento(agendamentoRecordInDto));
    }

}
