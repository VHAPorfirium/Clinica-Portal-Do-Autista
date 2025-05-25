package com.clinicaportaldoautista.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clinicaportaldoautista.dto.BlogPostDetailDTO;
import com.clinicaportaldoautista.dto.BlogPostRequestDTO;
import com.clinicaportaldoautista.dto.BlogPostSummaryDTO;
import com.clinicaportaldoautista.exception.ResourceNotFoundException;
import com.clinicaportaldoautista.model.BlogPost;
import com.clinicaportaldoautista.repository.BlogPostRepository;

@Service
public class BlogService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Transactional(readOnly = true)
    public Page<BlogPostSummaryDTO> listarTodosPosts(Pageable pageable) {
        return blogPostRepository.findAll(pageable).map(this::convertToSummaryDTO);
    }

    @Transactional(readOnly = true)
    public BlogPostDetailDTO obterPostPorSlug(String slug) {
        return blogPostRepository.findBySlug(slug)
                .map(this::convertToDetailDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Post não encontrado com o slug: " + slug));
    }

    @Transactional
    public BlogPostDetailDTO criarPost(BlogPostRequestDTO blogPostRequestDTO) {
        BlogPost novoPost = new BlogPost();
        novoPost.setTitulo(blogPostRequestDTO.getTitulo());
        novoPost.setConteudo(blogPostRequestDTO.getConteudo());
        novoPost.setAutor(blogPostRequestDTO.getAutor());
        novoPost.setUrlImagem(blogPostRequestDTO.getUrlImagem());

        if (blogPostRequestDTO.getSlug() != null && !blogPostRequestDTO.getSlug().trim().isEmpty()) {
            String slugFornecido = blogPostRequestDTO.getSlug().trim().toLowerCase().replaceAll("\\s+", "-");
            if (blogPostRepository.findBySlug(slugFornecido).isPresent()) {
                throw new IllegalArgumentException("O slug fornecido '" + slugFornecido + "' já existe. Por favor, escolha outro slug ou deixe em branco para geração automática.");
            }
            novoPost.setSlug(slugFornecido);
        } else {
            novoPost.setSlug(gerarSlugUnico(blogPostRequestDTO.getTitulo()));
        }

        BlogPost postSalvo = blogPostRepository.save(novoPost);
        return convertToDetailDTO(postSalvo);
    }


    private BlogPostSummaryDTO convertToSummaryDTO(BlogPost post) {
        String resumo = "";
        if (post.getConteudo() != null) {
            resumo = post.getConteudo().length() > 150 ? post.getConteudo().substring(0, 150) + "..." : post.getConteudo();
        }
        return new BlogPostSummaryDTO(
                post.getId(),
                post.getTitulo(),
                post.getSlug(),
                resumo,
                post.getUrlImagem(),
                post.getDataCriacao(),
                post.getAutor()
        );
    }

    private BlogPostDetailDTO convertToDetailDTO(BlogPost post) {
        return new BlogPostDetailDTO(
                post.getId(),
                post.getTitulo(),
                post.getSlug(),
                post.getConteudo(),
                post.getAutor(),
                post.getUrlImagem(),
                post.getDataCriacao(),
                post.getDataAtualizacao()
        );
    }

    private String gerarSlug(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            return "post-sem-titulo";
        }
        String slugBase = titulo.toLowerCase()
                .replaceAll("\\s+", "-")
                .replaceAll("[^a-z0-9-]", "");
        slugBase = slugBase.replaceAll("-+", "-");
        slugBase = slugBase.replaceAll("^-|-$", "");

        if (slugBase.isEmpty()) {
            return "post-generico";
        }
        return slugBase;
    }

    private String gerarSlugUnico(String titulo) {
        String slugBase = gerarSlug(titulo);
        String slugFinal = slugBase;
        int contador = 1;
        while (blogPostRepository.findBySlug(slugFinal).isPresent()) {
            slugFinal = slugBase + "-" + contador;
            contador++;
        }
        return slugFinal;
    }
}
