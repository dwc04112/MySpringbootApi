package kr.ac.daegu.springbootapi.board.model;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDAO {
    List<CommentDTO> getCommentList();

    // CommentDTO getCommentById(int id);
    List<CommentDTO> getCommentById(int id);

    // List<BoardDTO> commentBoardById(int id);

    // CommentDTO getCommentById2(int id, int cid);

    int postComment(CommentDTO commentDTO);

    int countBoard(int pass1);
}
