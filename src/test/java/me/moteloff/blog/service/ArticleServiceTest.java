package me.moteloff.blog.service;

import me.moteloff.blog.AbstractIntegrationTest;
import me.moteloff.blog.modules.Article;
import me.moteloff.blog.repositories.ArticleRepository;
import me.moteloff.blog.services.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArticleServiceTest extends AbstractIntegrationTest {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @BeforeEach
    void setUp() {
        articleService.deleteAll();
    }

    @Test
    public void shouldCreate() {
        Article article = new Article();
        article.setTitle("Test Article");
        article.setDescription("Test Article Description");
        articleRepository.save(article);

        Article result = articleService.create(article);

        assertEquals(article, result);
    }

    @Test
    public void shouldFind() {
        Article article = new Article();
        article.setTitle("Test Article");
        article.setDescription("Test Article Description");
        articleRepository.save(article);

        Article foundedArticle = articleService.find(article.getId());

        assertEquals(article, foundedArticle);
    }

    @Test
    public void shouldDeleteAll() {
        List<Article> articles = Arrays.asList(new Article(), new Article());
        articleRepository.saveAll(articles);

        articleService.deleteAll();

        assertThat(articleRepository.findAll()).isEmpty();
    }
}
