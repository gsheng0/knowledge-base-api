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
        ArrayList<Article> out = new ArrayList<>();

        return out;
    }

    @PostMapping("/select")
    public Article select(@RequestBody int id){
        Article first = new Article(0, "", "", "");
        return first;
    }

    @GetMapping("/view-all")
    public ArrayList<Article> viewAll() {
        return (ArrayList<Article>)DBHandler.getAllArticles();
    }

    @PostMapping("/save")
    public int saveArticle(@RequestBody Article article){
        int response = DBHandler.addArticle(article);
        if(response > 0)
            counter++;
        return response;
    }

    @PostMapping("/update")
    public void updateArticle(@RequestBody Article article){

    }

    @GetMapping("/create/{title}/{content}")
    public Article createArticle(@PathVariable String title, @PathVariable String content){
        Article article = new Article(counter, title, content, new Date().toString());
        DBHandler.addArticle(article);
        counter++;
        return article;
    }


}
