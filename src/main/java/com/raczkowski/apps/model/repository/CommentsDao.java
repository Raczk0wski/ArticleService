package com.raczkowski.apps.model.repository;

import com.raczkowski.apps.model.Article;
import com.raczkowski.apps.model.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentsDao {
    void addComment(Comment comment, Article article);

    List<Comment> showComment();

    List<Comment> commentsOfArticles(int idOfArticle);
}