package com.kraft.krafting.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kraft.krafting.service.KafkaProducerService;

@RestController
public class KafkaController {

    private final KafkaProducerService producerService;

    public KafkaController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/enviarMensaje")
    public ResponseEntity<String> enviarMensaje(@RequestBody String mensaje) {
        producerService.enviarMensaje(mensaje);
        return ResponseEntity.ok("Mensaje enviado con éxito");
    }
}

