package com.raczkowski.apps.model;

import com.raczkowski.apps.model.repository.ArticlesDao;
import com.raczkowski.apps.model.repository.ArticlesJDBCDao;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.time.LocalDate.now;
@Service
public class ArticleStatistics {
    ArticlesDao articlesDao = new ArticlesJDBCDao();

    public List<Article> articlesFromToday() {
        return getArticlesForPredicate(article -> article.getLocalDate().equals(now()));
    }

    public List<Article> articlesOfAuthor(String author) {
        return getArticlesForPredicate(article -> article.getAuthor().equalsIgnoreCase(author));
    }

    public List<Article> articlesFromRange(DataRange dataRange) {
        return getArticlesForPredicate(article -> article.getLocalDate().isAfter(dataRange.getStartRangeTime())
                && article.getLocalDate().isBefore(dataRange.getEndRangeTime()));
    }

    private List<Article> getArticlesForPredicate(Predicate<Article> predicate) {
        List<Article> articles = new ArrayList<>();
        for (Article article : articlesDao.loadArticles()) {
            if (predicate.test(article)) {
                articles.add(article);
            }
        }
        return articles;
    }

    public List<Article> articlesOfLongestContext() {
        List<Article> articles = new ArrayList<>();
        int counter = articlesDao.loadArticles().get(0).getContent().length();
        for (Article article : articlesDao.loadArticles()) {
            if (article.getContent().length() >= counter) {
                articles.add(0, article);
                counter = article.getContent().length();
            }
        }
        return articles;
    }

    public List<Article> articlesFilter(String choice) {
        List<Article> articlesSorted = new ArrayList<>(articlesDao.loadArticles());
        if(!choice.equals("Asc") && !choice.equals("Desc")) {
            return null;
        } else {
            Article temporaryArticle;
            if (articlesSorted.size() > 1) {
                if (choice.equals("Asc")) {
                    for (int i = 0; i < articlesSorted.size(); i++) {
                        for (int j = 0; j < articlesSorted.size() - 1; j++) {
                            if (articlesSorted.get(j).getLocalDate().
                                    isAfter(articlesSorted.get(j + 1).getLocalDate())) {
                                temporaryArticle = articlesSorted.get(j);
                                articlesSorted.set(j, articlesSorted.get(j + 1));
                                articlesSorted.set(j + 1, temporaryArticle);
                            }
                        }
                    }
                } else if (choice.equals("Desc")) {
                    for (int i = 0; i < articlesSorted.size(); i++) {
                        for (int j = 0; j < articlesSorted.size() - 1; j++) {
                            if (articlesSorted.get(j).getLocalDate().
                                    isBefore(articlesSorted.get(j + 1).getLocalDate())) {
                                temporaryArticle = articlesSorted.get(j);
                                articlesSorted.set(j, articlesSorted.get(j + 1));
                                articlesSorted.set(j + 1, temporaryArticle);
                            }
                        }
                    }
                }
            }
            return articlesSorted;
        }
    }
}
