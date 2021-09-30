package kr.ac.daegu.springbootapi.board.controller;

import kr.ac.daegu.springbootapi.board.model.BoardDTO;
import kr.ac.daegu.springbootapi.board.service.BoardService;
import kr.ac.daegu.springbootapi.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/board")
public class BoardController {

    public final BoardService boardService;

    @GetMapping(value = "/")
    public ApiResponse<BoardDTO> getBoardList(){
        List<BoardDTO> list = boardService.getBoardList();
        return new ApiResponse(true, list);
    }

    /* mission */
    // POST /board 해서 board 데이터 Insert 해보기
    @PostMapping(value = "/")
    public ApiResponse<BoardDTO> postBoard(@RequestBody BoardDTO boardDTO) throws Exception {
        BoardDTO dto = boardService.postBoard(boardDTO);

        return new ApiResponse(true, dto);
    }
    @PutMapping(value = "/{id}")
    public String putBoard(@PathVariable int id,
                           @RequestBody BoardDTO boardDTO) throws Exception {
        log.debug("id: " + id);
        return boardService.putBoard(id, boardDTO);
    }
}
    //post / board 해서 board 데이터 Insert 해보기

    /*
    public List<BoardDTO> rootBoard() throws Exception {

        List<BoardDTO> boardList = boardService.getBoardList();

        return boardList;
    }
     */

    /* --0929 미션
    //post / board 해서 board 데이터 Insert 해보기
    @PostMapping(value = "/")
    public String insertBoard(@RequestBody BoardDTO boardDTO) throws Exception{
        //0930 insert된 데이터가 뭔지 바로 리턴
        //BoardDTO dto = boardService.postBoard(boardDTO);
        log.debug("insertBoard");
        return boardService.insertBoard(boardDTO);
    }

     */


