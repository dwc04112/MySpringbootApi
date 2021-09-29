package kr.ac.daegu.springbootapi.board.controller;


import kr.ac.daegu.springbootapi.board.model.BoardDTO;
import kr.ac.daegu.springbootapi.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/board")
@RequiredArgsConstructor
@Slf4j

public class BoardController {
    // postman에서 http://localhost:8080/board/ 요청 날려서
    // DB의 board 테이블 데이터 모두 가져와서 json return해보기

    public final BoardService boardService;

    @GetMapping(value = "/")
    public List<BoardDTO> getBoardList(){
        List<BoardDTO> list = boardService.getBoardList();
        return list;
    }

    /*
    public List<BoardDTO> rootBoard() throws Exception {

        List<BoardDTO> boardList = boardService.getBoardList();

        return boardList;
    }

     */

}
