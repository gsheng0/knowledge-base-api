package com.george.blog.components;

public class SearchQuery {
    private String searchTerm;
    public SearchQuery(String searchTerm){
        this.searchTerm = searchTerm;
    }
    public String getSearchTerm() { return searchTerm; }
    public String toString() { return "Search Query: " + searchTerm; }
}
