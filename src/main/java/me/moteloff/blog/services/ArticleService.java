package me.moteloff.blog.services;

import lombok.RequiredArgsConstructor;
import me.moteloff.blog.modules.Article;
import me.moteloff.blog.repositories.ArticleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Article create(Article article) {
        return articleRepository.save(article);
    }

    public Article find(Long id) {
        return articleRepository.getArticleById(id);
    }

    public void deleteAll() {
        articleRepository.deleteAll();
    }

}
