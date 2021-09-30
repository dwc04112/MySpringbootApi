
package kr.ac.daegu.springbootapi.board.service;


import kr.ac.daegu.springbootapi.board.model.BoardDAO;
import kr.ac.daegu.springbootapi.board.model.BoardDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class BoardService {

    public final BoardDAO boardDAO;

    public List<BoardDTO> getBoardList() {
        return boardDAO.getBoardList();
    }

    public BoardDTO postBoard(BoardDTO boardDTO) throws Exception {
        log.debug(boardDTO.toString());

        // Inserted Date, Inserted Time 정의
        boardDTO.setWriteDate(LocalDate.now());
        boardDTO.setWriteTime(LocalTime.now());

        int insertedRowCount = boardDAO.postBoard(boardDTO);
        if(insertedRowCount > 0){
            return boardDTO;
        } else {
            throw new Exception("failed to insert board data");
        }
    }


        //id 해당 글 불러와서
        //author(없으면 그대로),
        //subject(없으면 그대로),
        //content(없으면 그대로),
        //writeDate, writeTime 업데이트
        public String putBoard(int id, BoardDTO boardDTO) throws Exception {
            // author,
            // content,
            // subject,
            // content
            // writeDate, writeTime 업데이트
            boardDTO.setId(id);
            boardDTO.setWriteDate(LocalDate.now());
            boardDTO.setWriteTime(LocalTime.now());
            int result = boardDAO.putBoard(boardDTO);

            if(result > 0){
                return result + " rows updated";
            }
            throw new Exception("failed to update " + id + " content");
        }


}

    /* 0929 미션 및 실행성공 코드
    public String insertBoard(BoardDTO boardDTO) throws Exception {
        // boardDTO db에 insert
        int result = boardDAO.insertBoard(boardDTO);
        return result + " rows inserted";
    }

     */

