package com.banco.bank_mobile_service.dto;
import java.time.LocalDateTime;
public record TransaccionEvent(Long cuentaId, String canal, String operacion,
                               String resultado, LocalDateTime fecha) {}
