package org.pgm.backend1.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.pgm.backend1.domain.Board;
import org.pgm.backend1.domain.Comment;
import org.pgm.backend1.dto.CommentDTO;
import org.pgm.backend1.repository.BoardRepository;
import org.pgm.backend1.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Override
    public Comment saveComment(Long boardId, CommentDTO commentDTO) { // 댓글 저장 방법: boardId를 받아서 board를 찾아서 comment에 저장한다. CommentDTO를 받아서 Comment로 변환한다.
        Board board = boardRepository.findById(boardId).orElseThrow();
        Comment comment = Comment.builder()
                .board(board)
                .content(commentDTO.getContent())
                .writer(commentDTO.getWriter())
                .build();
        if(commentDTO.getParentId() != null){ // 부모 댓글이 있을 경우
            Comment parent = commentRepository.findById(commentDTO.getParentId()).orElseThrow();
            comment.setParent(parent);
        }
        board.setCommentCount(board.getCommentCount() + 1);
        return commentRepository.save(comment);

    }

    // 1. 저장할때 board id를 받아서 board를 찾아서 comment에 저장한다.
// 2. CommentDTO를 받아서 Comment로 변환한다.
// 3. 부모 댓글이 있을 경우 부모 댓글을 찾아서 comment에 저장한다.
// 4. board의 댓글 수를 1 증가시킨다.
// 5. comment를 저장한다.

    @Override
    public List<Comment> getComments(Long boardId) { // 게시글의 댓글을 가져오기 위한 메소드
        Board board = boardRepository.findById(boardId).orElseThrow();

        return commentRepository.findByBoardAndParentIsNull(board);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);

    }

    @Override
    public Comment getComment(Long id) {
        return commentRepository.findById(id).orElseThrow();
    }
}




