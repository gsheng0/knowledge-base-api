package com.george.blog;

import com.george.blog.components.Article;
import com.george.blog.components.ArticleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@CrossOrigin("*")
@RestController
public class Controller {
    private final ArticleRepository repository;
    private int counter = 0;
    public Controller(ArticleRepository repository){
        this.repository = repository;
        this.counter = (int)repository.count();
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
        Article first = repository.findById(id).get(0);
        return first;
    }

    @GetMapping("/view-all")
    public ArrayList<Article> viewAll() {
        return (ArrayList<Article>)repository.findAll();
    }

    @PostMapping("/save")
    public void saveArticle(@RequestBody Article article){
        repository.save(article);
        counter++;
    }

    @PostMapping("/update")
    public void updateArticle(@RequestBody Article article){
        repository.save(article);
    }


}
