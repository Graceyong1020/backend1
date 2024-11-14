package org.pgm.backend1.repository;

import org.pgm.backend1.domain.Board;
import org.pgm.backend1.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBoardAndParentIsNull(Board board); //Board와 Parent가 null인 Comment를 찾는 메서드 즉, 대댓글이 아닌 댓글을 찾는 메서드

}
