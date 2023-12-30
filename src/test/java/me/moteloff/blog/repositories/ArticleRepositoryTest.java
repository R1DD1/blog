package me.moteloff.blog.repositories;

import me.moteloff.blog.AbstractIntegrationTest;
import me.moteloff.blog.modules.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ArticleRepositoryTest extends AbstractIntegrationTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ArticleRepository articleRepository;

    @BeforeEach
    void setUp() {
        articleRepository.deleteAll();
    }

    @Test
    public void shouldGetArticleById() {
        Article article = new Article();
        article.setTitle("Test Title");
        article.setDescription("Test Description");
        article = testEntityManager.persistAndFlush(article);

        Article foundArticle = articleRepository.getArticleById(article.getId());
        assertThat(foundArticle).isEqualTo(article);
    }
}
