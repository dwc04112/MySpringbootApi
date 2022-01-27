package kr.ac.daegu.springbootapi.boardjpa.controller;

import kr.ac.daegu.springbootapi.boardjpa.dto.BoardDTO;
import kr.ac.daegu.springbootapi.boardjpa.model.Board;
import kr.ac.daegu.springbootapi.boardjpa.service.BoardJpaService;
import kr.ac.daegu.springbootapi.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/boardjpa")
public class BoardJpaController {

    public final BoardJpaService boardJpaService;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping(value = "/list")
    public List<Board> SelectList(){
        return boardJpaService.getBoardListVue();
    }

    @GetMapping(value = "/{bid}")
    public List<Board> SelectListid(@PathVariable int bid, HttpServletRequest request, HttpServletResponse response){

        // boolean viewCookie = boardJpaService.viewCookie(bid,request,response);


        return boardJpaService.getBoardByBId2(bid);
    }

    @GetMapping(value = "/viewCount/{bid}")
    public void viewCount(@PathVariable int bid, HttpServletRequest request, HttpServletResponse response){
        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("viewCookie")) {
                    oldCookie = cookie;
                }
            }
        }
        if (oldCookie != null) {

            if (!oldCookie.getValue().contains("[" + bid + "]")) {
                log.debug("기존 쿠키가 있지만 해당 board 조회가 없을 때");
                boardJpaService.viewCountUp(bid);
                oldCookie.setValue(oldCookie.getValue() + "_[" + bid + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(oldCookie);
            }
        } else {
            log.debug("기존 쿠키가 없을 때");
            boardJpaService.viewCountUp(bid);
            Cookie newCookie = new Cookie("viewCookie","[" + bid + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 12);
            response.addCookie(newCookie);
        }
    }


    @PostMapping(value = "/write")
    public Board boardWrite(@RequestBody BoardDTO boardDTO){
        Board data = boardJpaService.boardWrite(boardDTO);
        return data;
    }

    @PostMapping(value = "/edit")
    public ApiResponse<BoardDTO> editBoard(@RequestBody BoardDTO boardDTO){
        return boardJpaService.editBoard(boardDTO);
    }


    // DELETE /boardjpa/{id} 구현할것.
    // logic : board.controller.BoardController의 로직 따를것
    //         무슨말이냐면 DB에 데이터를 DELETE 시키지 말고, board 컬럼 중 isDel 을 "Y"로 업데이트.
    @PostMapping(value = "/delete")
    public ApiResponse<BoardDTO> updateIsDelBoardById(@RequestBody BoardDTO boardDTO){
        return boardJpaService.updateIsDelBoardById(boardDTO);
    }

/*
    @GetMapping(value = "/")
    public ApiResponse<BoardDTO> getBoardList(@RequestParam int page, @RequestParam int size,
                                              @RequestParam(required = false) String stype , @RequestParam(required = false) String svalue){
        log.debug("검색 값 "+stype+" 그리고 " +svalue);
        Page<Board> list = boardJpaService.getBoardList(page, size, stype ,svalue);
        return new ApiResponse(true, list);
    }


    @GetMapping(value = "/{id}") // PathVariable
    public ApiResponse<BoardDTO> getBoardById(@PathVariable Integer id){
        log.debug("hihi id : " + id);
        Board data = boardJpaService.getBoardById(id);
        return new ApiResponse(true, data);
    }



    @PostMapping(value = "/")
    public ApiResponse<BoardDTO> postBoard(@RequestBody BoardDTO boardDTO){
        log.debug("author="+boardDTO.getAuthor());
        Board data = boardJpaService.postBoard(boardDTO);
        return new ApiResponse(true, data);
    }

    @PutMapping(value = "/{id}")
    public ApiResponse<BoardDTO> putBoard(@PathVariable int id,
                                          @RequestBody BoardDTO boardDTO){
        Board data = boardJpaService.putBoard(id, boardDTO);
        return new ApiResponse(true, data);
    }

    // DELETE /boardjpa/{id} 구현할것.
    // logic : board.controller.BoardController의 로직 따를것
    //         무슨말이냐면 DB에 데이터를 DELETE 시키지 말고, board 컬럼 중 isDel 을 "Y"로 업데이트.
    @DeleteMapping(value = "/{id}")
    public ApiResponse<BoardDTO> updateIsDelBoardById(@PathVariable int id,
                                                      @RequestBody BoardDTO boardDTO){
        String boardPassword = boardDTO.getPassword();
        log.debug("request.id=" + id);
        log.debug("request.password=" + boardPassword);
        // password가 없을 경우
        if(boardPassword == null){
            return new ApiResponse<>(false, "boardPassword is null, please check key name 'password'", null);
        }
        return boardJpaService.updateIsDelBoardById(id, boardPassword);
    }

    // mission 답글 쓰기를 담당하는 기능 구현.
    @PostMapping(value = "/reply")
    public ApiResponse<BoardDTO> postBoardReplyContent(@RequestBody BoardDTO boardDTO){
        if(boardDTO.getDepth()>=2){
            return new ApiResponse<>(false,"The maximum value cannot exceed 3", null);
        }
        return boardJpaService.postReply(boardDTO);
    }


 */


}