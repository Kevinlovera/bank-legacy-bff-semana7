package com.banco.bank_web_service.dto;

import java.time.LocalDateTime;

/**
 * Evento publicado en Kafka cada vez que el canal WEB
 * consulta información de una cuenta.
 */
public record TransaccionEvent(
        Long cuentaId,
        String canal,
        String operacion,
        String resultado,
        LocalDateTime fecha
) {
}