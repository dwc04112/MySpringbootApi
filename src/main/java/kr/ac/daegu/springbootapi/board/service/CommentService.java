package kr.ac.daegu.springbootapi.board.service;



import kr.ac.daegu.springbootapi.board.model.BoardDAO;
import kr.ac.daegu.springbootapi.board.model.BoardDTO;
import kr.ac.daegu.springbootapi.board.model.CommentDAO;
import kr.ac.daegu.springbootapi.board.model.CommentDTO;
import kr.ac.daegu.springbootapi.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CommentService {

    public final CommentDAO commentDAO;

    public List<CommentDTO> getCommentList() {
        return commentDAO.getCommentList();
    }

    public List<CommentDTO> getCommentById(int id) {
        return commentDAO.getCommentById(id);
    }

    /*
    public List<BoardDTO> commentBoardById(int id) {
        return commentDAO.commentBoardById(id);
    }

     */


/*
    public ApiResponse<CommentDTO> commentBoardById2(int id) {
        ArrayList list = new ArrayList();

        for(int cid =0; cid<=100; cid++) {

                if(null!=commentDAO.getCommentById2(id, cid)){
                    list.add(commentDAO.getCommentById2(id, cid));
                }

        }
        return new ApiResponse(true,list);
    }

 */
    public ApiResponse<CommentDTO> postComment(CommentDTO commentDTO)throws Exception {

        int pass2 = commentDAO.countBoard(commentDTO.getId());
        log.debug("postComment id2 ::: " +pass2);
        if(pass2==0){
            return new ApiResponse (false, "id value is not exists in board",null);
        }else{
            int result = commentDAO.postComment(commentDTO);
            if(result > 0){
                return new ApiResponse(true,"Success to post comment in board id : "+commentDTO.getId(),commentDTO);
            }else{
                throw new Exception("Falied to post comment in board");
            }
        }
    }
}
