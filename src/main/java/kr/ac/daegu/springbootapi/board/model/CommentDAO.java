package kr.ac.daegu.springbootapi.board.model;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDAO {
    List<CommentDTO> getCommentList();
}
