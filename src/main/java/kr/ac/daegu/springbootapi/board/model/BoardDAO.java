package kr.ac.daegu.springbootapi.board.model;
import kr.ac.daegu.springbootapi.comment.model.CommentDTO;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BoardDAO {


    int putBoard(BoardDTO boardDTO);

    List<BoardDTO> getBoardList();

    int postBoard(BoardDTO boardDTO);

    BoardDTO getBoardById(int id);

    int updateIsDelBoardById(int id);

    List<CommentDTO> getBoardComment(int id);


     BoardDTO getBoardById2(int id);
     List<CommentDTO> comments(int id);

    BoardDTO getBoardByIdOri(int id);

    //List<BoardDTO> getBoardById2(int id);
}

// 0929
// int insertBoard(BoardDTO boardDTO);

    /* 동작하는 쿼리 0930
    //글 읽기
    List<BoardDTO> getBoardRead(int id);

    //글 삭제
    int deleteBoard(int id);
     */