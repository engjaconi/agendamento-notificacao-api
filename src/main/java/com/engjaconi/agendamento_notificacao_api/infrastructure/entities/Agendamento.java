package com.engjaconi.agendamento_notificacao_api.infrastructure.entities;

import com.engjaconi.agendamento_notificacao_api.infrastructure.enums.StatusNotificacaoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "TB_AGENDAMENTO")
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AGD_ID")
    private Long id;
    @Column(name = "AGD_EMAIL_DESTINATARIO")
    private String emailDestinatario;
    @Column(name = "AGD_TELEFONE_DESTINATARIO")
    private String telefoneDestinatario;
    @Column(name = "AGD_DATA_HORA_ENVIO")
    private LocalDateTime dataHoraEnvio;
    @Column(name = "AGD_DATA_HORA_AGENDAMENTO")
    private LocalDateTime dataHoraAgendamento;
    @Column(name = "AGD_DATA_HORA_MODIFICACAO")
    private LocalDateTime dataHoraModificacao;
    @Column(name = "AGD_MENSAGEM")
    private String mensagem;
    @Column(name = "AGD_STATUS_NOTIFICACAO")
    private StatusNotificacaoEnum statusNotificacao;

    @PrePersist
    private void prePersist() {
        dataHoraAgendamento = LocalDateTime.now();
        statusNotificacao = StatusNotificacaoEnum.AGENDADO;
    }

}
