package me.moteloff.blog.controllers;

import lombok.RequiredArgsConstructor;
import me.moteloff.blog.modules.Article;
import me.moteloff.blog.services.ArticleService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping(path = "/create/article", consumes = MediaType.ALL_VALUE)
    public String createArticle(@RequestBody Article article) {
        articleService.create(article);
        return "Article " + article.getId() + " was created";
    }
}
