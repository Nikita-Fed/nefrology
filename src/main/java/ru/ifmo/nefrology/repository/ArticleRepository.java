package ru.ifmo.nefrology.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.ifmo.nefrology.entity.Article;

import java.util.Optional;

public interface ArticleRepository extends CrudRepository<Article, Integer> {
    @Query("SELECT u FROM Article u WHERE u.title =:title")
    Optional<Article> findByTitle(@Param("title") String title);
}
