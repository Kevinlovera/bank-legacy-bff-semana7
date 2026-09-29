package com.banco.bank_atm_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
    "spring.cloud.config.enabled=false",
    "spring.cloud.discovery.enabled=false",
    "eureka.client.enabled=false",
    "spring.kafka.listener.auto-startup=false"
})
class AtmApplicationTests {
    @Test
    void contextLoads() {}
}
