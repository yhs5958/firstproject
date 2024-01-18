package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test; //테스트 패키지 임포트 기본.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*; //앞으로 사용할 수있는 패키지 임포트.
import org.springframework.transaction.annotation.Transactional;
@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;
    @Test // 해당 메서드가 테스트 코드임을선언.
    void index() {
        Article a= new Article(1L,"ㄱㄱㄱㄱ","1111");
        Article b= new Article(2L,"ㄴㄴㄴㄴ","2222");
        Article c= new Article(3L,"ㄷㄷㄷㄷ","3333");
        List<Article>expected= new ArrayList<Article>(Arrays.asList(a, b, c));

        List<Article> articles = articleService.index();

        assertEquals(expected.toString(),articles.toString());
    }

    @Test
    void show_성공_존재하는_id_입력() {
        Long id = 1L;
        Article expected = new Article(id, "ㄱㄱㄱㄱ", "1111");

        Article article = articleService.show(id);

        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_실패_존재하지_않는_id_입력() {
        Long id = -1L;
        Article expected = null;
        Article article = articleService.show(id);
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_성공_title과_content만_있는_dto_입력() {
        String title = "ㄹㄹㄹㄹ";
        String content = "44444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);
        Article article = articleService.create(dto);
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void create_실패_id가_포함된_dto_입력() {
        Long id = 4L;
        String title = "ㄹㄹㄹㄹ";
        String content = "44444";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;
        Article article = articleService.create(dto);
        assertEquals(expected, article);
    }

//update()를 성공한 경우
    @Test
    @Transactional
    void update_성공_존재하는_id와_title_content가_있는_dto입력(){
        //예상 데이터
        Long id = 1L;
        String title = "가나다라";
        String content = "1234";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(id, title, content);
        Article article = articleService.update(id, dto);
        assertEquals(expected.toString(), article.toString());
    }

//update()를 성공한 경우2
    @Test
    @Transactional
    void update_성공_존재하는_id와_title만_있는_dto입력(){
        //예상 데이터
        Long id = 1L;
        String title = "AAAA";
        String content = null;
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(1L, "AAAA", "1111");
        Article article = articleService.update(id, dto);
        assertEquals(expected.toString(), article.toString());
    }

//update()를 실패한 경우
    @Test
    @Transactional
    void update_실패_존재하지_않는_id와_dto입력(){
        //예상 데이터
        Long id = -1L;
        String title = "가나다라";
        String content = "1234";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;
        Article article = articleService.update(id, dto);
        assertEquals(expected, article);
    }

//delete()를 성공한 경우
    @Test
    @Transactional
    void delete_성공_존재하는_id_입력(){
        //예상 데이터
        Long id = 1L;
        Article expected = new Article(id, "ㄱㄱㄱㄱ", "1111");
        Article article = articleService.delete(id);
        assertEquals(expected.toString(), article.toString());
    }

//delete()를 실패한 경우
    @Test
    @Transactional
    void delete_실패_존재하지_않는_id_입력(){
        //예상 데이터
        Long id = -1L;
        Article expected = null;
        Article article = articleService.delete(id);
        assertEquals(expected, article);
    }

}