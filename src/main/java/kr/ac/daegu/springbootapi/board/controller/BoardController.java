package kr.ac.daegu.springbootapi.board.controller;


import kr.ac.daegu.springbootapi.board.model.BoardDTO;
import kr.ac.daegu.springbootapi.board.service.BoardService;
import kr.ac.daegu.springbootapi.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public ApiResponse<BoardDTO> getBoardList(){
        List<BoardDTO> list = boardService.getBoardList();
        return new ApiResponse(true, list);
    }
    //post / board 해서 board 데이터 Insert 해보기

    /*
    public List<BoardDTO> rootBoard() throws Exception {

        List<BoardDTO> boardList = boardService.getBoardList();

        return boardList;
    }
     */

    //post / board 해서 board 데이터 Insert 해보기
    @PostMapping(value = "/")
    public String insertBoard(@RequestBody BoardDTO boardDTO) throws Exception{
        //0930 insert된 데이터가 뭔지 바로 리턴
        //BoardDTO dto = boardService.postBoard(boardDTO);
        log.debug("insertBoard");
        return boardService.insertBoard(boardDTO);
    }

}
