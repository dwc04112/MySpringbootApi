package kr.ac.daegu.springbootapi.board.controller;

import kr.ac.daegu.springbootapi.board.model.CommentDTO;
import kr.ac.daegu.springbootapi.board.service.CommentService;
import kr.ac.daegu.springbootapi.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/comment")
public class CommentController {

    public final CommentService commentService;
    /*1001~1004까지의 미션
     *
     * */
    @GetMapping(value = "/")
    public ApiResponse<CommentDTO> getCommentList(){
        List<CommentDTO> list = commentService.getCommentList();
        return new ApiResponse(true, list);
    }
}
