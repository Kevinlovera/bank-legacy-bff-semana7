package com.banco.bank_web_service.service;

import com.banco.bank_web_service.dto.CuentaResponse;
import com.banco.bank_web_service.dto.TransaccionEvent;
import com.banco.bank_web_service.event.TransaccionProducer;
import com.banco.bank_web_service.model.Cuenta;
import com.banco.bank_web_service.repository.CuentaRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Contiene la lógica de negocio del canal WEB.
 */
@Service
public class CuentaWebService {

    private final CuentaRepository repository;
    private final TransaccionProducer producer;

    public CuentaWebService(
            CuentaRepository repository,
            TransaccionProducer producer) {

        this.repository = repository;
        this.producer = producer;
    }

    /**
     * Consulta una cuenta y publica un evento de auditoría.
     *
     * Resilience4j protege la operación ante fallos del acceso
     * a la fuente de datos.
     */
    @CircuitBreaker(
            name = "cuentaService",
            fallbackMethod = "consultarCuentaFallback"
    )
    public CuentaResponse consultarCuenta(Long cuentaId) {

        Cuenta cuenta = repository.buscarPorId(cuentaId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Cuenta no encontrada: " + cuentaId
                        )
                );

        producer.publicar(
                new TransaccionEvent(
                        cuentaId,
                        "WEB",
                        "CONSULTAR_CUENTA",
                        "EXITOSA",
                        LocalDateTime.now()
                )
        );

        return new CuentaResponse(
                cuenta.getCuentaId(),
                cuenta.getNombre(),
                cuenta.getSaldo(),
                cuenta.getTipo(),
                cuenta.getSaldoFinal(),
                cuenta.getEstado()
        );
    }

    /**
     * Respuesta de contingencia utilizada por Resilience4j.
     */
    public CuentaResponse consultarCuentaFallback(
            Long cuentaId,
            Throwable exception) {

        return new CuentaResponse(
                cuentaId,
                "Servicio temporalmente no disponible",
                null,
                null,
                null,
                "FALLBACK"
        );
    }
}