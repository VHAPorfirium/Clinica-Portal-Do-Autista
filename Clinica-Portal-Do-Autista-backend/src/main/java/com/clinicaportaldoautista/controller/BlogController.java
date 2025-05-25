package com.clinicaportaldoautista.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinicaportaldoautista.dto.BlogPostDetailDTO;
import com.clinicaportaldoautista.dto.BlogPostRequestDTO;
import com.clinicaportaldoautista.dto.BlogPostSummaryDTO;
import com.clinicaportaldoautista.service.BlogService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/blog-posts")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public ResponseEntity<Page<BlogPostSummaryDTO>> listarPosts(
            @PageableDefault(size = 10, sort = "dataCriacao", direction = org.springframework.data.domain.Sort.Direction.DESC) Pageable pageable) {
        Page<BlogPostSummaryDTO> posts = blogService.listarTodosPosts(pageable);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<BlogPostDetailDTO> obterPostPorSlug(@PathVariable String slug) {
        BlogPostDetailDTO post = blogService.obterPostPorSlug(slug);
        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<BlogPostDetailDTO> criarPost(@Valid @RequestBody BlogPostRequestDTO blogPostRequestDTO) {
        BlogPostDetailDTO postCriado = blogService.criarPost(blogPostRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(postCriado);
    }
}
