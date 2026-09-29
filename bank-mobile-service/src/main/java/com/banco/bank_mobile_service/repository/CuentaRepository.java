package com.banco.bank_mobile_service.repository;

import com.banco.bank_mobile_service.model.Cuenta;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class CuentaRepository {
    private final JdbcTemplate jdbcTemplate;

    public CuentaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Cuenta> findById(Long cuentaId) {
        String sql = "SELECT cuenta_id, nombre, saldo, edad, tipo, interes, saldo_final, estado " +
                     "FROM cuentas_bff WHERE cuenta_id = ?";
        return jdbcTemplate.query(sql, rs -> {
            if (!rs.next()) return Optional.empty();
            Cuenta c = new Cuenta();
            c.setCuentaId(rs.getLong("cuenta_id"));
            c.setNombre(rs.getString("nombre"));
            c.setSaldo(rs.getBigDecimal("saldo"));
            c.setEdad(rs.getInt("edad"));
            c.setTipo(rs.getString("tipo"));
            c.setInteres(rs.getBigDecimal("interes"));
            c.setSaldoFinal(rs.getBigDecimal("saldo_final"));
            c.setEstado(rs.getString("estado"));
            return Optional.of(c);
        }, cuentaId);
    }
}
