package com.george.blog.components;

public class UploadedArticle {
    private String title;
    private String content;
    public UploadedArticle(String title, String content){
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
