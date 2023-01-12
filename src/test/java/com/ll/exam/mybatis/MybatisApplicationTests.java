package com.ll.exam.mybatis;

import com.ll.exam.mybatis.article.dto.Article;
import com.ll.exam.mybatis.article.service.ArticleService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MybatisApplicationTests {
	@Autowired
	private ArticleService articleService;

	@Test
	@DisplayName("게시물들 조회")
	void t1() {
		List<Article> articles = articleService.getArticles();
		System.out.println(articles);
	}

	@Test
	@DisplayName("게시물 작성")
	void t2() {
		long id = articleService.write("제목3", "내용3");

		assertThat(id).isGreaterThan(0);
	}

}
