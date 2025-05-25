package com.clinicaportaldoautista.dto;

import java.time.LocalDateTime;

public class BlogPostSummaryDTO {
    private Long id;
    private String titulo;
    private String slug;
    private String resumo;
    private String urlImagem;
    private LocalDateTime dataCriacao;
    private String autor;


    public BlogPostSummaryDTO(Long id, String titulo, String slug, String resumo, String urlImagem, LocalDateTime dataCriacao, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.slug = slug;
        this.resumo = resumo;
        this.urlImagem = urlImagem;
        this.dataCriacao = dataCriacao;
        this.autor = autor;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }
    public String getResumo() { return resumo; }
    public void setResumo(String resumo) { this.resumo = resumo; }
    public String getUrlImagem() { return urlImagem; }
    public void setUrlImagem(String urlImagem) { this.urlImagem = urlImagem; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
}