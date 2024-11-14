package org.pgm.backend1.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private String content;
    private String writer;
    private Long parentId; // entity는 parentId를 못 받기 때문에 DTO에 추가
}
