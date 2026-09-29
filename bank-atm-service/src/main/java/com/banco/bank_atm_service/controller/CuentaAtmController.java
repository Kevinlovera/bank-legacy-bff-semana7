package com.banco.bank_atm_service.controller;

import com.banco.bank_atm_service.dto.CuentaResponse;
import com.banco.bank_atm_service.service.CuentaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/atm/cuentas")
public class CuentaAtmController {
    private final CuentaService service;

    public CuentaAtmController(CuentaService service) {
        this.service = service;
    }

    @GetMapping("/{cuentaId}/saldo")
    public CuentaResponse consultar(@PathVariable Long cuentaId) {
        return service.consultar(cuentaId);
    }
}
