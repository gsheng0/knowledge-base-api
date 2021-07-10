package com.george.blog.components;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Article {
    @Id
    private int id;

    private String title;
    private String content;
    private String date;

    public Article(int id, String title, String content, String date){
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;

    }

    public String getTitle() { return title; }
    public String getContent() { return content; }
    public int getId() { return id; }
    public String getDate() { return date; }

}
