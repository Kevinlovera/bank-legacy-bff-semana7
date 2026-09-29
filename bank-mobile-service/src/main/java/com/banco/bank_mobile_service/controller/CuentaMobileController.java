package com.banco.bank_mobile_service.controller;

import com.banco.bank_mobile_service.dto.CuentaResponse;
import com.banco.bank_mobile_service.service.CuentaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mobile/cuentas")
public class CuentaMobileController {
    private final CuentaService service;

    public CuentaMobileController(CuentaService service) {
        this.service = service;
    }

    @GetMapping("/{cuentaId}")
    public CuentaResponse consultar(@PathVariable Long cuentaId) {
        return service.consultar(cuentaId);
    }
}
