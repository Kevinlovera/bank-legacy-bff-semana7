package com.banco.bank_web_service.dto;

import java.math.BigDecimal;

/**
 * Respuesta pública del endpoint WEB.
 */
public record CuentaResponse(
        Long cuentaId,
        String nombre,
        BigDecimal saldo,
        String tipo,
        BigDecimal saldoFinal,
        String estado
) {
}