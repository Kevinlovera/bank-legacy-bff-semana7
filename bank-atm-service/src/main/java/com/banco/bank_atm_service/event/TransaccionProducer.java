package com.banco.bank_atm_service.event;

import com.banco.bank_atm_service.dto.TransaccionEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TransaccionProducer {
    private static final String TOPIC = "banco.transacciones";
    private final KafkaTemplate<String, TransaccionEvent> kafkaTemplate;

    public TransaccionProducer(KafkaTemplate<String, TransaccionEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publicar(TransaccionEvent evento) {
        kafkaTemplate.send(TOPIC, evento.cuentaId().toString(), evento);
    }
}
