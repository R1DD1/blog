package me.moteloff.blog.repositories;

import me.moteloff.blog.modules.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article getArticleById(Long id);
}
