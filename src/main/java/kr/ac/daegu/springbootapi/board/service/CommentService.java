package kr.ac.daegu.springbootapi.board.service;



import kr.ac.daegu.springbootapi.board.model.CommentDAO;
import kr.ac.daegu.springbootapi.board.model.CommentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CommentService {

    public final CommentDAO commentDAO;

    public List<CommentDTO> getCommentList() {
        return commentDAO.getCommentList();
    }
}
