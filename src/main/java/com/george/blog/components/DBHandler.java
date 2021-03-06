package com.george.blog.components;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class DBHandler {
    public static JdbcTemplate template;
    public static boolean ready = false;

    public static void setTemplate(JdbcTemplate template){
        DBHandler.template = template;
        DBHandler.ready = template == null;
    }

    public static int insertArticle(Article article){
        int id = article.getId();
        String date = article.getDate();
        String content = article.getContent();
        String title = article.getTitle();

        String command = "INSERT INTO articles (title, content, id, date) VALUES (?, ?, ?, ?)";
        return template.update(command, title, content, id, date);
    }

    public static List<Article> getAllArticles(){
        String command = "SELECT * FROM articles";

        return template.query(command, new ArticleRowMapper());
    }

    public static int updateContent(int id, String content){
        String command = "UPDATE articles SET content = ? WHERE id = ?";
        return template.update(command, content, id);
    }

    public static int updateTitle(int id, String title){
        String command = "UPDATE articles SET title = ? WHERE id = ?";
        return template.update(command, title, id);
    }

    public static int updateArticle(int id, String title, String content){
        String command = "UPDATE articles SET title = ? content = ? WHERE id = ?";
        return template.update(command, title, content, id);
    }

    public static int updateArticle(Article article){
        return updateArticle(article.getId(), article.getTitle(), article.getContent());
    }

    public static Article getArticleById(int id){
        String command = "SELECT * FROM articles WHERE id = ?";
        return template.queryForObject(command, new ArticleRowMapper(), id);
    }

    public static List<Article> searchByTerm(String term){
        String command = "SELECT * FROM articles WHERE MATCH (title, content) AGAINST (\"" + term + "\" IN NATURAL LANGUAGE MODE)";
        return template.query(command, new ArticleRowMapper());
    }

    public static int getArticleCount(){
        String command = "SELECT count(id) FROM articles";
        return template.queryForObject(command, Integer.class);
    }

    public static List<Article> getMostRecentArticles(int n){
        String command = "SELECT * FROM articles ORDER BY id DESC LIMIT " + n;
        return template.query(command, new ArticleRowMapper());
    }

    public static int deleteById(int id){
        String command = "DELETE FROM articles WHERE id = ?";
        return template.update(command, id);
    }
}
