package me.moteloff.blog.controllers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import me.moteloff.blog.AbstractIntegrationTest;
import me.moteloff.blog.services.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;

class ArticleControllerTest extends AbstractIntegrationTest {
    @LocalServerPort
    private Integer port;

    @Autowired
    private ArticleService articleService;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        articleService.deleteAll();
    }

    @Test
    public void shouldCreateArticle() {
        given()
                .body("{ \"title\": \"test\", \"description\": \"desc\" }")
                .contentType(ContentType.JSON)
                .post("/create/article")
                .then()
                .assertThat().statusCode(200)
                .log().all();
    }
}
