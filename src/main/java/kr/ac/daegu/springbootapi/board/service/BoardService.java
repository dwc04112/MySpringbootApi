package kr.ac.daegu.springbootapi.board.service;

import kr.ac.daegu.springbootapi.board.model.BoardDAO;
import kr.ac.daegu.springbootapi.board.model.BoardDTO;
import kr.ac.daegu.springbootapi.test.model.TestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    public final BoardDAO boardDAO;

    public List<BoardDTO> getBoardList() {
        return boardDAO.getBoardList();
    }

    public String insertBoard(BoardDTO boardDTO) throws Exception {
        // boardDTO db에 insert
        int result = boardDAO.insertBoard(boardDTO);
        return result + " rows inserted";
    }
}
