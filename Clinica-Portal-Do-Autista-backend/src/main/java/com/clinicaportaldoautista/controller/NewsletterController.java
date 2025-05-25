package com.clinicaportaldoautista.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinicaportaldoautista.dto.GenericResponseDTO;
import com.clinicaportaldoautista.dto.NewsletterSubscriptionRequest;
import com.clinicaportaldoautista.model.NewsletterSubscriber;
import com.clinicaportaldoautista.service.NewsletterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/newsletter")
public class NewsletterController {

    @Autowired
    private NewsletterService newsletterService;

    @PostMapping("/subscribe")
    public ResponseEntity<?> inscreverNaNewsletter(@Valid @RequestBody NewsletterSubscriptionRequest request) {
        NewsletterSubscriber assinante = newsletterService.inscrever(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(new GenericResponseDTO("Inscrição realizada com sucesso! Email: " + assinante.getEmail()));
    }
}