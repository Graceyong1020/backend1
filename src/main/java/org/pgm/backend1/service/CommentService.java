package org.pgm.backend1.service;

import org.pgm.backend1.domain.Comment;
import org.pgm.backend1.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    Comment saveComment(Long boardId, CommentDTO commentDTO);

    List<Comment> getComments(Long boardId); // 게시글의 댓글을 가져오기 위한 메소드
    void deleteComment(Long id); // 댓글 삭제
    Comment getComment(Long id); // 댓글 가져오기
}
