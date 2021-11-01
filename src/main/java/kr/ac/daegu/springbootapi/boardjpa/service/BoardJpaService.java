package kr.ac.daegu.springbootapi.boardjpa.service;

import kr.ac.daegu.springbootapi.board.model.BoardDTO;
import kr.ac.daegu.springbootapi.boardjpa.model.Board;
import kr.ac.daegu.springbootapi.boardjpa.model.BoardRepository;
import kr.ac.daegu.springbootapi.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class BoardJpaService {

    public final BoardRepository boardRepository;

    public Page<Board> getBoardList(int page, int size) {
        // 숙제 2 : jpa queryMethod를 수정하여 isDel이 "N"인 데이터row들만 나오도록 수정
        PageRequest pageRequest = PageRequest.of(page, size);
        return boardRepository.findBoardsByIsDel("N", pageRequest);
    }

    public Board getBoardById(Integer id) {
        return boardRepository.findBoardById(id);
    }

    public Board postBoard(BoardDTO boardDTO) {
        Board postData = Board.builder()
                .author(boardDTO.getAuthor())
                .subject(boardDTO.getSubject())
                .content(boardDTO.getContent())
                .password(boardDTO.getPassword())
                .commentCount(0)
                .depth(0)
                .orderNum(0)
                .isDel("N")
                .readCount(0)
                .replyRootId(0)
                .writeDate(LocalDate.now())
                .writeTime(LocalTime.now())
                .build();
        return boardRepository.save(postData);
    }

    public Board putBoard(int id, BoardDTO boardDTO) {
        Optional<Board> boardData = boardRepository.findBoardById(id);

        // 람다식을 사용하여
        boardData.ifPresent(selectedBoard -> {
            selectedBoard.setAuthor(boardDTO.getAuthor());
            selectedBoard.setSubject(boardDTO.getSubject());
            selectedBoard.setContent(boardDTO.getContent());
            selectedBoard.setPassword(boardDTO.getPassword());
            boardRepository.save(selectedBoard);
        });

        return boardData.orElseGet(boardData::get);
    }

    public ApiResponse<BoardDTO> updateIsDelBoard(int id, BoardDTO boardDTO) {
        // 비밀번호 일치 확인
        Optional<Board> oriPass = boardRepository.findBoardById(id);
        // 위 oriPass 가 null 이면 RuntimeException 발생시키고 메소드 종료.
        Board data = oriPass.orElseThrow(() -> new RuntimeException("no data"));
        // 비번 일치하지 않으면?
        boolean isPwdMatch = data.getPassword().equals(boardDTO.getPassword());
        if (!isPwdMatch){
            return new ApiResponse<>(false, "board password is not match, please check requested board password");
        }
        // 비밀번호 일치시 IsDel : Y 로 변경
        Optional<Board> boardData = boardRepository.findBoardById(id);
        boardData.ifPresent(selectedBoard ->{
            selectedBoard.setIsDel("Y");
            boardRepository.save(selectedBoard);
        });

        return new ApiResponse<>(true, "board id " + id + " is successfully deleted");
    }
}