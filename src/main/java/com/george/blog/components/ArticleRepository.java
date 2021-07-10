package com.george.blog.components;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Integer> {
    List<Article> findById(int id);

    @Query("SELECT * FROM articles WHERE MATCH (title) AGAINST (?1) ")
    List<Article> searchByTitle(String searchTerm);

}
