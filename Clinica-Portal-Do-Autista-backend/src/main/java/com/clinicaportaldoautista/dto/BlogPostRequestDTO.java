package com.clinicaportaldoautista.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BlogPostRequestDTO {

    @NotBlank(message = "O título não pode estar vazio.")
    @Size(min = 5, max = 255, message = "O título deve ter entre 5 e 255 caracteres.")
    private String titulo;

    @Size(max = 300, message = "O slug não pode exceder 300 caracteres.")
    private String slug;

    @NotBlank(message = "O conteúdo não pode estar vazio.")
    private String conteudo;

    @Size(max = 100, message = "O nome do autor não pode exceder 100 caracteres.")
    private String autor;

    @Size(max = 500, message = "A URL da imagem não pode exceder 500 caracteres.")
    private String urlImagem;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }
}
