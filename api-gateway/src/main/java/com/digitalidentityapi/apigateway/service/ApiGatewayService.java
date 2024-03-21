package com.digitalidentityapi.apigateway.service;

import com.digitalidentityapi.apigateway.dto.CitizenDto;
import com.digitalidentityapi.apigateway.dto.DocumentDto;
import com.digitalidentityapi.apigateway.dto.TransferRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ApiGatewayService {

    private final WebClient webClient;
    private static final String BROKER_INTERMEDIARY_BASE_URL = "34.118.205.255:8080";
    private static final String CITIZEN_BASE_URL = "34.172.37.211:8080";

    @Autowired
    public ApiGatewayService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<ResponseEntity<String>> createCitizen(CitizenDto citizenDto) {
        return webClient.post()
                .uri(BROKER_INTERMEDIARY_BASE_URL + "/broker-intermediary/citizen")
                .bodyValue(citizenDto)
                .retrieve()
                .toEntity(String.class);
    }

    public Mono<ResponseEntity<String>> updateCitizen(CitizenDto citizenDto) {
        return webClient.put()
                .uri(BROKER_INTERMEDIARY_BASE_URL + "/broker-intermediary/citizen")
                .bodyValue(citizenDto)
                .retrieve()
                .toEntity(String.class);
    }

    public Mono<ResponseEntity<String>> deleteCitizen(CitizenDto citizenDto) {
        return webClient.method(HttpMethod.DELETE)
                .uri(BROKER_INTERMEDIARY_BASE_URL + "/broker-intermediary/citizen")
                .bodyValue(citizenDto)
                .retrieve()
                .toEntity(String.class);
    }

    public Mono<ResponseEntity<String>> createDocument(DocumentDto documentDto) {
        return webClient.post()
                .uri(BROKER_INTERMEDIARY_BASE_URL + "/broker-intermediary/document")
                .bodyValue(documentDto)
                .retrieve()
                .toEntity(String.class);
    }

    public Mono<ResponseEntity<String>> updateDocument(DocumentDto documentDto) {
        return webClient.put()
                .uri(BROKER_INTERMEDIARY_BASE_URL + "/broker-intermediary/document")
                .bodyValue(documentDto)
                .retrieve()
                .toEntity(String.class);
    }

    public Mono<ResponseEntity<String>> deleteDocument(DocumentDto documentDto) {
        return webClient.method(HttpMethod.DELETE)
                .uri(BROKER_INTERMEDIARY_BASE_URL + "/broker-intermediary/document")
                .bodyValue(documentDto)
                .retrieve()
                .toEntity(String.class);
    }

    public Mono<ResponseEntity<String>> performTransfer(TransferRequestDto transferRequestDto) {
        return webClient.post()
                .uri(BROKER_INTERMEDIARY_BASE_URL + "/broker-intermediary/transfers")
                .bodyValue(transferRequestDto)
                .retrieve()
                .toEntity(String.class);
    }

    public Mono<ResponseEntity<List<DocumentDto>>> getAllDocumentsByEmail(String email) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(CITIZEN_BASE_URL + "/citizen-service/documents")
                        .queryParam("email", email)
                        .build())
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<DocumentDto>>() {});
    }
}
