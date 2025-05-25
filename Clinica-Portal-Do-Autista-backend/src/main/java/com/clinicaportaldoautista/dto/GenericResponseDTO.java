package com.clinicaportaldoautista.dto;

public class GenericResponseDTO {
    private String mensagem;

    public GenericResponseDTO(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}