package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        /* Case 1: 4번 게시글의 모든 댓글 조회 */
        {
            Long articleId = 4L;
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            Article article = new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ");
            Comment a = new Comment(1L, article, "park", "굿윌헌팅");
            Comment b = new Comment(2L, article, "kim", "아이엠샘");
            Comment c = new Comment(3L, article, "choi", "쇼행크탈출");
            List<Comment> expected = Arrays.asList(a, b, c);
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글 출력");
        }

        /* Case 2: 1번 게시글의 모든 댓글 조회 */
        {
            Long articleId = 1L;
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            Article article = new Article(1L, "ㄱㄱㄱㄱ", "1111");
            List<Comment> expected = Arrays.asList();
            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글이 없음.");
        }

        /* Case 3: 9번 게시글의 모든 댓글 조회 */
        {
            Long aritcleId = 9L;
            List<Comment> comments = commentRepository.findByArticleId(aritcleId);
            Article article = null;
            List<Comment> expected = Arrays.asList();
            assertEquals(expected.toString(), comments.toString(),"9번 글 자체가 없어서 댓글은 비어있어야 함.");
        }

        /* Case 4: 999번 게시글의 모든 댓글 조회 */
        {
            Long aritcleId = 999L;
            List<Comment> comments = commentRepository.findByArticleId(aritcleId);
            Article article = null;
            List<Comment> expected = Arrays.asList();
            assertEquals(expected.toString(), comments.toString(),"999번 글 자체가 없어서 댓글은 비어있어야 함.");
        }

        /* Case 5: -1번 게시글의 모든 댓글 조회 */
        {
            Long aritcleId = -1L;
            List<Comment> comments = commentRepository.findByArticleId(aritcleId);
            Article article = null;
            List<Comment> expected = Arrays.asList();
            assertEquals(expected.toString(), comments.toString(),"-1번 글 자체가 없어서 댓글은 비어있어야 함.");
        }

    }
    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        /* Case1:"park"의 모든 댓글 조회 */
        {
         String nickname = "park";
         List<Comment> comments = commentRepository.findByNickname(nickname);
         Comment a = new Comment(1L, new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ"), nickname, "굿윌헌팅");
         Comment b = new Comment(4L, new Article(5L, "당신의 인생 음식은?", "댓글 ㄱ"), nickname, "치킨");
         Comment c = new Comment(7L, new Article(6L, "당신의 취미는?", "댓글 ㄱ"), nickname, "독서");
         List<Comment> expected = Arrays.asList(a, b, c);
         assertEquals(expected.toString(), comments.toString(), "park의 모든 댓글 출력");
        }

        /* Case2:"kim"의 모든 댓글 조회 */
        {
            String nickname = "kim";
            List<Comment> comments = commentRepository.findByNickname(nickname);
            Comment a = new Comment(2L, new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ"), nickname, "아이엠샘");
            Comment b = new Comment(5L, new Article(5L, "당신의 인생 음식은?", "댓글 ㄱ"), nickname, "초밥");
            Comment c = new Comment(8L, new Article(6L, "당신의 취미는?", "댓글 ㄱ"), nickname, "조깅");
            List<Comment> expected = Arrays.asList(a, b, c);
            assertEquals(expected.toString(), comments.toString(), "kim의 모든 댓글 출력");
        }

        /* Case3:"null"의 모든 댓글 조회 */
        {
            String nickname = null;
            List<Comment> comments = commentRepository.findByNickname(nickname);
            List<Comment> expected = Arrays.asList();
            assertEquals(expected.toString(), comments.toString(), "null의 모든 댓글 출력");
        }

        /* Case4:" "의 모든 댓글 조회 */
        {   String nickname = "";
            List<Comment> comments = commentRepository.findByNickname(nickname);
            List<Comment> expected = Arrays.asList();
            assertEquals(expected.toString(), comments.toString(), "\"\"의 모든 댓글 출력");
        }
    }

}