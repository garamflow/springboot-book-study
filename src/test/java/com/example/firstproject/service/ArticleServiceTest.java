package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Coffee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test
    void index() {
        Article a = new Article(1L, "가가가", "111");
        Article b = new Article(1L, "나나나", "222");
        Article c = new Article(1L, "다다다", "333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));

        List<Article> articleList = articleService.index();

        assertEquals(expected.toString(), articleList.toString());
    }

    @Test
    void show_성공_존재하는_id_입력() {
        Long id = 1L;

        Article expected = new Article(1L, "가가가", "111");

        Article article = articleService.show(id);

        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_실패_존재하지_않는_id_입력() {
        Long id = 1L;

        Article expected = null;

        Article article = articleService.show(id);

        assertEquals(expected, article);
    }

    @Test
    void create_성공_title과_content만_있는_dto_입력() {
        String title = "라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);

        Article article = articleService.create(dto);

        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void create_실패_id가_포함된_dto_입력() {
        Long id = 4L;
        String title = "라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;

        Article article = articleService.create(dto);

        assertEquals(expected, article);
    }

    @Test
    void update_성공_존재하는_id와_title_content가_있는_dto_입력() {
        Long id = 1L;
        String title = "가가가가가";
        String content = "11111";
        ArticleForm dto = new ArticleForm(id, title, content);

        Article expected = new Article(id, title, content);

        Article coffee = articleService.update(id, dto);

        assertEquals(expected.toString(), coffee.toString());
    }

    @Test
    void update_성공_존재하는_id와_title만_있는_dto_입력() {
        Long id = 1L;
        String title = "가가가가가가가";
        String content = null;
        ArticleForm dto = new ArticleForm(id, title, content);

        Article expected = new Article(id, title, "111");

        Article article = articleService.update(id, dto);

        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void update_실패_존재하지_않는_id의_dto_입력() {
        Long id = -1L;
        String title = "가가가가가가가가";
        String content = "123123";
        ArticleForm dto = new ArticleForm(id, title, content);

        Article expected = null;

        Article article = articleService.update(id, dto);

        assertEquals(expected, article);
    }

    @Test
    void delete_성공_존재하는_id_입력() {
        Long id = 1L;

        Article expected = new Article(id, "가가가", "111");

        Article article = articleService.delete(id);

        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void delete_실패_존재하지_않는_id_입력() {
        Long id = -1L;

        Article expected = null;

        Article article = articleService.delete(id);

        assertEquals(expected, article);
    }
}