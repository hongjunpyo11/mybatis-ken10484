package com.ll.exam.mybatis.article.service;

import com.ll.exam.mybatis.article.dto.Article;
import com.ll.exam.mybatis.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> getArticles() {
        return articleRepository.getArticles();
    }

    public long write(String subject, String content) {
        articleRepository.write(subject, content);

        return articleRepository.getLastInsertId();
    }
}
