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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequiredArgsConstructor
@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoRecordOutDto> buscarAgendamentoPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(agendamentoService.buscarAgendamentoPorId(id));
    }
    
    @PostMapping
    public ResponseEntity<AgendamentoRecordOutDto> salvarAgendamento(@RequestBody AgendamentoRecordInDto agendamentoRecordInDto) {
        return ResponseEntity.ok(agendamentoService.salvarAgendamento(agendamentoRecordInDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarAgendamento(@PathVariable("id") Long id) {
        agendamentoService.cancelarAgendamento(id);
        return ResponseEntity.accepted().build();
    }

}
