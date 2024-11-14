package org.pgm.backend1.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String writer;
    private Integer commentCount;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime regdate;

    @OneToMany(mappedBy = "board",cascade = CascadeType.ALL)
    private List<Comment> comments; //댓글 리스트

    @PrePersist
    public void prePersist() {
        this.commentCount = commentCount == null ? 0 : commentCount;
    }

}
