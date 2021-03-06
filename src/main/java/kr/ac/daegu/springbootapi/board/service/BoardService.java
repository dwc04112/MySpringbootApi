
package kr.ac.daegu.springbootapi.board.service;


import kr.ac.daegu.springbootapi.board.model.BoardDAO;
import kr.ac.daegu.springbootapi.board.model.BoardDTO;
import kr.ac.daegu.springbootapi.comment.model.CommentDTO;
import kr.ac.daegu.springbootapi.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

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


        //id 해당 글 불러와서 글 수정 (git 수정되나?)
        //author(없으면 그대로),
        //subject(없으면 그대로),
        //content(없으면 그대로),
        //writeDate, writeTime 업데이트
        public ApiResponse<BoardDTO> putBoard(int id, BoardDTO boardDTO) throws Exception {
            //비번 검사
            String pass1 = boardDAO.getBoardById(id).getPassword();
            String pass2 = boardDTO.getPassword();
            log.debug("service password1 ::: "+pass1);
            log.debug("service password2 ::: "+pass2);
            if(!pass1.equals(pass2)){
                log.debug("비번 불일치");
                return new ApiResponse( false,"password incorrect in board id : " + id,null);
            }
            log.debug("비번 일치");
            boardDTO.setId(id);
            boardDTO.setWriteDate(LocalDate.now());
            boardDTO.setWriteTime(LocalTime.now());
            int result = boardDAO.putBoard(boardDTO);
            if(result > 0){
                return new ApiResponse(true ,"success to update board id : " + id);
            }

            throw new Exception("failed to update " + id + " content");
        }


    public ApiResponse<BoardDTO> getBoardById(int id) {
        BoardDTO data = boardDAO.getBoardById(id);
        List<CommentDTO> cData = boardDAO.getBoardComment(id);
        String isDel1 = boardDAO.getBoardByIdOri(id).getIsDel();
        if(isDel1.equals("Y")){
            return new ApiResponse(false,"board id "+ id +" is already deleted");
        }else {
            // data.setComments(cData);
            LinkedHashMap<String,Object> member = new LinkedHashMap<>();
            member.put("id",data.getId());
            member.put("author",data.getAuthor());
            member.put("subject",data.getSubject());
            member.put("content",data.getContent());
            member.put("comments",cData);

            return new ApiResponse(true, member);
            //return new ApiResponse(true, data);
        }
    }

    // Board테이블의 isDel 컬럼의 데이터를 'Y' 로 업데이트
    public ApiResponse<BoardDTO> updateIsDelBoardById (int id, BoardDTO boardDTO) throws Exception {
        String pass1 = boardDAO.getBoardById(id).getPassword();
        String pass2 = boardDTO.getPassword();
        log.debug("service password1 ::: "+pass1);
        log.debug("service password2 ::: "+pass2);
        if(pass1.equals(pass2)){
            log.debug("비번 일치");
            int updatedRow = boardDAO.updateIsDelBoardById(id);
            if(updatedRow > 0) {
                return new ApiResponse(true, "success to delete board id : " + id);
            }
        }else{
            log.debug("비번 불일치");
            return new ApiResponse(false, "password incorrect in board id : " + id, null);
        }
        throw new Exception("failed to delete board id : +id");
    }




}

    /* 0929 미션 및 실행성공 코드
    public String insertBoard(BoardDTO boardDTO) throws Exception {
        // boardDTO db에 insert
        int result = boardDAO.insertBoard(boardDTO);
        return result + " rows inserted";
    }

     */

/*
       // 실행 성공 코드
       // 0930 글 읽기
        public List<BoardDTO> getBoardRead(int id) {
            log.debug("id ::: " + id);
            return boardDAO.getBoardRead(id);
        }

        //글 삭제
    public String deleteBoard(int id) throws Exception {

        int result = boardDAO.deleteBoard(id);
        if(result > 0){
            return result + "rows deleted";
        }
        throw new Exception("failed to delete"+id+"content");
    }
 */

