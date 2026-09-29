package com.banco.bank_atm_service.dto;
import java.math.BigDecimal;
public record CuentaResponse(Long cuentaId, String nombre, BigDecimal saldo,
                             String tipo, BigDecimal saldoFinal, String estado) {}
