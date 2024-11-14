package org.pgm.backend1.service;

import org.pgm.backend1.domain.Board;

import java.util.List;

public interface BoardService {

        void insert(Board board);
        List<Board> list();
        Board findById(Long num);
        void update(Board board);
        void delete(Long num);
    }
