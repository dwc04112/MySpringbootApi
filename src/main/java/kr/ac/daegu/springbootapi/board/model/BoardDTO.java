package kr.ac.daegu.springbootapi.board.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class BoardDTO {
    private int id;                      // 글 id(글번호)
    private String author;               // 작성자 이름
    private String subject;              // 글 제목
    private String content;              // 글 컨텐츠
    private Date writeDate;              // 작성 날짜
    private Time writeTime;              // 작성 시간
    private int readCount;               // 조회수
    private int commentCount;            // 댓글 갯수
    private String password;             // 수정 삭제를 위한 패스워드
    private int replyRootId;
    private int depth;
    private int orderNum;
}
