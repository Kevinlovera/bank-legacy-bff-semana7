package com.banco.bank_web_service.controller;

import com.banco.bank_web_service.dto.CuentaResponse;
import com.banco.bank_web_service.service.CuentaWebService;
import org.springframework.web.bind.annotation.*;

/**
 * API REST correspondiente al canal WEB.
 */
@RestController
@RequestMapping("/api/web/cuentas")
public class CuentaWebController {

    private final CuentaWebService service;

    public CuentaWebController(CuentaWebService service) {
        this.service = service;
    }

    /**
     * Consulta información de una cuenta.
     *
     * Ejemplo:
     * GET /api/web/cuentas/101
     */
    @GetMapping("/{cuentaId}")
    public CuentaResponse consultarCuenta(
            @PathVariable Long cuentaId) {

        return service.consultarCuenta(cuentaId);
    }
}