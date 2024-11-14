package org.pgm.backend1.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.pgm.backend1.domain.Board;
import org.pgm.backend1.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional //2개 이상의 쿼리를 하나의 트랜잭션으로 처리하기 위해 사용
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository; // boardserviceimpl에서 boardRepository를 사용할때 값이 안 바뀜

    @Override
    public void insert(Board board) {
        boardRepository.save(board);

    }

    @Override
    public List<Board> list() {
        return boardRepository.findAll();
    }

    @Override
    public Board findById(Long id) {
        //Optional<Board> board = boardRepository.findById(id);
        return boardRepository.findById(id).orElseThrow(null);
    }

    @Override
    public void update(Board board) {
        Board oldBoard = boardRepository.findById(board.getId()).orElseThrow(null);
        oldBoard.setTitle(board.getTitle());
        oldBoard.setContent(board.getContent());
        boardRepository.save(oldBoard);
    }

    @Override
    public void delete(Long id) {
        boardRepository.deleteById(id);

    }
}
