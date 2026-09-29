package com.banco.bank_mobile_service.service;

import com.banco.bank_mobile_service.dto.CuentaResponse;
import com.banco.bank_mobile_service.dto.TransaccionEvent;
import com.banco.bank_mobile_service.event.TransaccionProducer;
import com.banco.bank_mobile_service.model.Cuenta;
import com.banco.bank_mobile_service.repository.CuentaRepository;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class CuentaService {
    private final CuentaRepository repository;
    private final TransaccionProducer producer;
    private final CircuitBreakerFactory<?, ?> circuitBreakerFactory;

    public CuentaService(CuentaRepository repository, TransaccionProducer producer,
                         CircuitBreakerFactory<?, ?> circuitBreakerFactory) {
        this.repository = repository;
        this.producer = producer;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    public CuentaResponse consultar(Long cuentaId) {
        return circuitBreakerFactory.create("cuentas").run(
            () -> consultarInterno(cuentaId),
            ex -> fallback(cuentaId)
        );
    }

    private CuentaResponse consultarInterno(Long cuentaId) {
        Cuenta cuenta = repository.findById(cuentaId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + cuentaId));

        publicar(cuentaId, "OK");
        return new CuentaResponse(cuenta.getCuentaId(), cuenta.getNombre(), cuenta.getSaldo(),
                                  cuenta.getTipo(), cuenta.getSaldoFinal(), cuenta.getEstado());
    }

    private CuentaResponse fallback(Long cuentaId) {
        publicar(cuentaId, "FALLBACK");
        return new CuentaResponse(cuentaId, "SERVICIO NO DISPONIBLE", null, null, null, "FALLBACK");
    }

    private void publicar(Long cuentaId, String resultado) {
        try {
            producer.publicar(new TransaccionEvent(cuentaId, "MOBILE", "CONSULTAR_CUENTA",
                                                   resultado, LocalDateTime.now()));
        } catch (Exception ignored) {
            // La mensajería no debe bloquear la consulta HTTP.
        }
    }
}
