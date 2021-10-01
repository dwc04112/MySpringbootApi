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

    /* mission 1001 글 읽기 & 글 삭제 리뷰 */
    // 글 읽기
    // 요청URL은 GET http://localhost:8080/board/{id}
    @GetMapping(value = "/{id}")
    public ApiResponse<BoardDTO> getBoardById(@PathVariable int id) throws Exception {
        return boardService.getBoardById(id);
    }

    // 글 삭제
    // isDeleted : Y로 업데이트 시킴.
    // 요청URL은 DELETE http://localhost:8080/board/{id}
    @DeleteMapping(value = "/{id}")
    public ApiResponse<BoardDTO> updateIsDelBoardById(@PathVariable int id) throws Exception {
        return boardService.updateIsDelBoardById(id);
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


    /*
    //mission 0930 글 읽기 삭제
    // 글 읽기
    // 요청URL은 GET http://localhost:8080/board/{id}
    //수정해야함 0930
    @GetMapping(value = "/{id}")
    public ApiResponse<BoardDTO> getBoardRead(@PathVariable int id){
        log.debug("id: " + id);
        List<BoardDTO> list= boardService.getBoardRead(id);
        return new ApiResponse(true, list);

    }
    // 글 삭제
    // 요청URL은 DELETE http://localhost:8080/board/{id}
    @DeleteMapping(value = "/{id}")
    public String deleteBoard(@PathVariable int id) throws Exception{
        log.debug("id : " + id);
        return boardService.deleteBoard(id);
    }

     */




