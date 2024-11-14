package org.pgm.backend1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.pgm.backend1.domain.Comment;
import org.pgm.backend1.dto.CommentDTO;
import org.pgm.backend1.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{boardId}") // postmapping 자체가 create를 의미
    public ResponseEntity<Long> addComment(@PathVariable("boarId") Long boardId,
                                        @RequestBody CommentDTO commentDTO) {
        Comment comment = commentService.saveComment(boardId, commentDTO);
        return ResponseEntity.ok(comment.getId());

    }
    @GetMapping("/{boardId}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable("boardId") Long boardId) {
        return ResponseEntity.ok(commentService.getComments(boardId));
    }
    @GetMapping("/one/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable("id") Long id) {
        return ResponseEntity.ok(commentService.getComment(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteComment(@PathVariable("id") Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok(id);
    }
}
