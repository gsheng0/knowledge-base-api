package com.george.blog.components;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleRowMapper implements RowMapper<Article> {

    @Override
    public Article mapRow(ResultSet rs, int row) throws SQLException{
        Article article = new Article(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getString("date"));
        return article;
    }
}
