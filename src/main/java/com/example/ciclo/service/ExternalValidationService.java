package com.example.ciclo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExternalValidationService {

    private final WebClient webClient;

    public ExternalValidationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public boolean verificarCamionActivo(Long camionId) {
        try {
            Boolean existe = this.webClient.get()
                .uri("http://localhost:8084/api/v1/camiones/" + camionId + "/activo")
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
            return existe != null && existe;
        } catch (Exception e) {
            return true;
        }
    }

    public boolean verificarPalaActiva(Long palaId) {
        try {
            Boolean existe = this.webClient.get()
                .uri("http://localhost:8083/api/v1/palas/" + palaId + "/activa")
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
            
            return existe != null && existe;
        } catch (Exception e) {
            return true;
        }
    }

    public boolean verificarPaleroAutorizado(Long paleroId) {
        try {
            Boolean existe = this.webClient.get()
                .uri("http://localhost:8081/api/v1/usuarios/paleros/" + paleroId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
            return existe != null && existe;
        } catch (Exception e) {
            return true;
        }
    }

    public boolean verificarMaterialValido(Long materialId) {
        try {
            Boolean existe = this.webClient.get()
                .uri("http://localhost:8083/api/v1/materiales/" + materialId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
            return existe != null && existe;
        } catch (Exception e) {
            return true;
        }
    }
}
