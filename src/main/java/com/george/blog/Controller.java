package com.george.blog;

import com.george.blog.components.Article;
import com.george.blog.components.DBHandler;
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
        counter = DBHandler.getAllArticles().size();
    }

    @GetMapping("/")
    public String home(){
        return "Blog Page Home";
    }

    @PostMapping("/search")
    public ArrayList<Article> search(String searchTerm){
        return (ArrayList<Article>)DBHandler.searchByTerm(searchTerm);
    }

    @PostMapping("/select")
    public Article select(@RequestBody int id){
        return DBHandler.getArticleById(id);
    }

    @GetMapping("/view-all")
    public ArrayList<Article> viewAll() {
        return (ArrayList<Article>)DBHandler.getAllArticles();
    }

    @PostMapping("/save")
    public boolean saveArticle(@RequestBody Article article){
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
