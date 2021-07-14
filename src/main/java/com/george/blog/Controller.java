package com.george.blog;

import com.george.blog.components.Article;
import com.george.blog.components.DBHandler;
import com.george.blog.components.SearchQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;

@CrossOrigin("*")
@RestController
public class Controller {
    @Autowired
    private JdbcTemplate template;
    private int counter = 0;

    @PostConstruct
    public void init(){
        DBHandler.setTemplate(template);
        counter = DBHandler.getMostRecentArticle().getId() + 1;
    }

    @GetMapping("/")
    public String home(){
        return "Blog Page Home";
    }

    @PostMapping("/search")
    public ArrayList<Article> search(@RequestBody String query){
        System.out.println("SEARCH TERM: " + query);
        System.out.println(query);
        return (ArrayList<Article>)DBHandler.searchByTerm(query);
    }

    @PostMapping("/get")
    public Article select(@RequestBody int id){
        return DBHandler.getArticleById(id);
    }

    @GetMapping("/view-all")
    public ArrayList<Article> viewAll() {
        return (ArrayList<Article>)DBHandler.getAllArticles();
    }

    @PostMapping("/upload")
    public boolean uploadArticle(@RequestBody String content, @RequestBody String title){
        Article article = new Article(counter, title, content, new Date().toString());
        int response = DBHandler.insertArticle(article);
        if(response > 0)
            counter++;
        return response <= 0;
    }

    @PostMapping("/update")
    public boolean updateArticle(@RequestBody Article article){
        int response = DBHandler.updateArticle(article);
        return response <= 0;
    }

    @PostMapping("/delete")
    public boolean deleteArticle(@RequestBody int id){
        int response = DBHandler.deleteById(id);
        return response <= 0;
    }

    @PostMapping("/update/title")
    public boolean updateTitle(@RequestBody int id, @RequestBody String title){
        int response  = DBHandler.updateTitle(id, title);
        return response <= 0;
    }

    @PostMapping("/update/content")
    public boolean updateContent(@RequestBody int id, @RequestBody String content){
        int response = DBHandler.updateContent(id, content);
        return response <= 0;
    }

    @GetMapping("/create/{title}/{content}")
    public Article createArticle(@PathVariable String title, @PathVariable String content){
        Article article = new Article(counter, title, content, new Date().toString());
        DBHandler.insertArticle(article);
        counter++;
        return article;
    }

    @GetMapping("/update/{content}")
    public boolean updateArticle(@PathVariable String content){
        int response = DBHandler.updateContent(counter - 1, content);
        return response <= 0;
    }


}
