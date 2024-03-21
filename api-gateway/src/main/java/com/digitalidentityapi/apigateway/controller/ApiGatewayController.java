package com.digitalidentityapi.apigateway.controller;

import com.digitalidentityapi.apigateway.dto.SignDocumentDto;
import com.digitalidentityapi.apigateway.service.ApiGatewayService;
import com.digitalidentityapi.apigateway.dto.CitizenDto;
import com.digitalidentityapi.apigateway.dto.DocumentDto;
import com.digitalidentityapi.apigateway.dto.TransferRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api-gateway")
public class ApiGatewayController {

    private final ApiGatewayService apiGatewayService;

    @Autowired
    public ApiGatewayController(ApiGatewayService apiGatewayService) {
        this.apiGatewayService = apiGatewayService;
    }

    @PostMapping("/citizens")
    public Mono<ResponseEntity<String>> createCitizen(@RequestBody CitizenDto citizenDto) {
        return apiGatewayService.createCitizen(citizenDto);
    }

    @PutMapping("/citizens")
    public Mono<ResponseEntity<String>> updateCitizen(@RequestBody CitizenDto citizenDto) {
        return apiGatewayService.updateCitizen(citizenDto);
    }

    @DeleteMapping("/citizens")
    public Mono<ResponseEntity<String>> deleteCitizen(@RequestBody CitizenDto citizenDto) {
        return apiGatewayService.deleteCitizen(citizenDto);
    }

    @PostMapping("/documents")
    public Mono<ResponseEntity<String>> createDocument(@RequestBody DocumentDto documentDto) {
        return apiGatewayService.createDocument(documentDto);
    }

    @PutMapping("/documents")
    public Mono<ResponseEntity<String>> updateDocument(@RequestBody DocumentDto documentDto) {
        return apiGatewayService.updateDocument(documentDto);
    }

    @DeleteMapping("/documents")
    public Mono<ResponseEntity<String>> deleteDocument(@RequestBody DocumentDto documentDto) {
        return apiGatewayService.deleteDocument(documentDto);
    }

    @PostMapping("/transfers")
    public Mono<ResponseEntity<String>> performTransfer(@RequestBody TransferRequestDto transferRequestDto) {
        return apiGatewayService.performTransfer(transferRequestDto);
    }

    @PostMapping("/sign-document")
    public Mono<ResponseEntity<String>> performTransfer(@RequestBody SignDocumentDto signDocumentDto) {
        return apiGatewayService.signDocument(signDocumentDto);
    }

    @GetMapping("/documents/{email}")
    public Mono<ResponseEntity<List<DocumentDto>>> getAllDocumentsByEmail(@PathVariable String email) {
        return apiGatewayService.getAllDocumentsByEmail(email);
    }

    @PostMapping("/transfers/confirm")
    public String callBackUrlTransfer() {
        return "Transferencia procesada con exito";
    }
}
