package com.banco.bank_web_service.event;

import com.banco.bank_web_service.dto.TransaccionEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Publica eventos de negocio en Kafka.
 */
@Component
public class TransaccionProducer {

    private static final String TOPIC = "banco.transacciones";

    private final KafkaTemplate<String, TransaccionEvent> kafkaTemplate;

    public TransaccionProducer(
            KafkaTemplate<String, TransaccionEvent> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    public void publicar(TransaccionEvent evento) {

        kafkaTemplate.send(
                TOPIC,
                evento.cuentaId().toString(),
                evento
        );
    }
}