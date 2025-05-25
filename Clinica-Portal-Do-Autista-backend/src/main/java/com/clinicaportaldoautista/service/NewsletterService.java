package com.clinicaportaldoautista.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clinicaportaldoautista.dto.NewsletterSubscriptionRequest;
import com.clinicaportaldoautista.exception.SubscriptionException;
import com.clinicaportaldoautista.model.NewsletterSubscriber;
import com.clinicaportaldoautista.repository.NewsletterSubscriberRepository;

@Service
public class NewsletterService {

    @Autowired
    private NewsletterSubscriberRepository subscriberRepository;

    @Transactional
    public NewsletterSubscriber inscrever(NewsletterSubscriptionRequest requestDTO) {
        if (subscriberRepository.existsByEmail(requestDTO.getEmail())) {
            throw new SubscriptionException("Este email já está inscrito na newsletter.");
        }

        NewsletterSubscriber novoAssinante = new NewsletterSubscriber();
        novoAssinante.setNomeCompleto(requestDTO.getNomeCompleto());
        novoAssinante.setEmail(requestDTO.getEmail());
        novoAssinante.setNumeroTelefone(requestDTO.getNumeroTelefone());
        novoAssinante.setAtivo(true);

        return subscriberRepository.save(novoAssinante);
    }
}