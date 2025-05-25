package com.clinicaportaldoautista.dto;

import java.time.LocalDateTime;

public class BlogPostDetailDTO {
    private Long id;
    private String titulo;
    private String slug;
    private String conteudo;
    private String autor;
    private String urlImagem;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public BlogPostDetailDTO(Long id, String titulo, String slug, String conteudo, String autor, String urlImagem, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        this.id = id;
        this.titulo = titulo;
        this.slug = slug;
        this.conteudo = conteudo;
        this.autor = autor;
        this.urlImagem = urlImagem;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }
    public String getConteudo() { return conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public String getUrlImagem() { return urlImagem; }
    public void setUrlImagem(String urlImagem) { this.urlImagem = urlImagem; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }
}