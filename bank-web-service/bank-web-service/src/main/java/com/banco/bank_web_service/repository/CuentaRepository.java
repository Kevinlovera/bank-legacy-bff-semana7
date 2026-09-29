package com.banco.bank_web_service.repository;

import com.banco.bank_web_service.model.Cuenta;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Acceso a los datos migrados del sistema legacy.
 */
@Repository
public class CuentaRepository {

    private final JdbcTemplate jdbcTemplate;

    public CuentaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Cuenta> buscarPorId(Long cuentaId) {

        String sql = """
                SELECT cuenta_id,
                       nombre,
                       saldo,
                       edad,
                       tipo,
                       interes,
                       saldo_final,
                       estado
                FROM cuentas_bff
                WHERE cuenta_id = ?
                """;

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {

                    Cuenta cuenta = new Cuenta();

                    cuenta.setCuentaId(rs.getLong("cuenta_id"));
                    cuenta.setNombre(rs.getString("nombre"));
                    cuenta.setSaldo(rs.getBigDecimal("saldo"));
                    cuenta.setEdad(rs.getInt("edad"));
                    cuenta.setTipo(rs.getString("tipo"));
                    cuenta.setInteres(rs.getBigDecimal("interes"));
                    cuenta.setSaldoFinal(rs.getBigDecimal("saldo_final"));
                    cuenta.setEstado(rs.getString("estado"));

                    return cuenta;
                },
                cuentaId
        ).stream().findFirst();
    }
}