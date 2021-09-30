package kr.ac.daegu.springbootapi.board.model;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BoardDAO {
    int putBoard(BoardDTO boardDTO);

    List<BoardDTO> getBoardList();

    // 0929
    // int insertBoard(BoardDTO boardDTO);

    int postBoard(BoardDTO boardDTO);
}
