package com.george.blog.components;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class DBHandler {
    public static String table = "articles";
    public static JdbcTemplate template;
    public static boolean ready = false;

    public static void setTemplate(JdbcTemplate template){
        DBHandler.template = template;
        DBHandler.ready = template == null;
    }

    public static int addArticle(Article article){
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
}
