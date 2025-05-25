package com.clinicaportaldoautista.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NewsletterSubscriptionRequest {

    @NotBlank(message = "O nome completo não pode estar vazio.")
    @Size(min = 3, max = 255, message = "O nome completo deve ter entre 3 e 255 caracteres.")
    private String nomeCompleto;

    @NotBlank(message = "O email não pode estar vazio.")
    @Email(message = "Formato de email inválido.")
    @Size(max = 255, message = "O email não pode exceder 255 caracteres.")
    private String email;

    @Size(max = 20, message = "O número de telefone não pode exceder 20 caracteres.")
    private String numeroTelefone;

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }
}