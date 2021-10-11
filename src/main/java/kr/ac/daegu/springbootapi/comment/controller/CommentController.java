package kr.ac.daegu.springbootapi.comment.controller;

import kr.ac.daegu.springbootapi.comment.model.CommentDTO;
import kr.ac.daegu.springbootapi.comment.service.CommentService;
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

    @GetMapping(value = "/{id}")
    public ApiResponse<CommentDTO> getCommentById(@PathVariable int id)throws Exception{
        List<CommentDTO> Clist = commentService.getCommentById(id);
      // List<BoardDTO> Blist = commentService.commentBoardById(id);


     //   return commentService.commentBoardById2(id);

       // ArrayList list1 = new ArrayList();
       // ArrayList list2 = new ArrayList();
       // list1.add(commentService.commentBoardById2(id));
       // list1.add(list2);
       // list2.add(commentService.getCommentById(id));


       // return new ApiResponse(true, list1);
        return new ApiResponse(true, Clist);
    }

    //3. 댓글 쓰기
    @PostMapping(value = "/")
    public ApiResponse<CommentDTO> postComment(@RequestBody CommentDTO commentDTO)throws Exception{
        return commentService.postComment(commentDTO);

    }
    
}
