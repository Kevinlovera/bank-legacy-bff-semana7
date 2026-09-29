package com.banco.bank_atm_service.model;

import java.math.BigDecimal;

public class Cuenta {
    private Long cuentaId;
    private String nombre;
    private BigDecimal saldo;
    private Integer edad;
    private String tipo;
    private BigDecimal interes;
    private BigDecimal saldoFinal;
    private String estado;

    public Long getCuentaId() { return cuentaId; }
    public void setCuentaId(Long v) { cuentaId = v; }
    public String getNombre() { return nombre; }
    public void setNombre(String v) { nombre = v; }
    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal v) { saldo = v; }
    public Integer getEdad() { return edad; }
    public void setEdad(Integer v) { edad = v; }
    public String getTipo() { return tipo; }
    public void setTipo(String v) { tipo = v; }
    public BigDecimal getInteres() { return interes; }
    public void setInteres(BigDecimal v) { interes = v; }
    public BigDecimal getSaldoFinal() { return saldoFinal; }
    public void setSaldoFinal(BigDecimal v) { saldoFinal = v; }
    public String getEstado() { return estado; }
    public void setEstado(String v) { estado = v; }
}
