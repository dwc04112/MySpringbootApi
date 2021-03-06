package kr.ac.daegu.springbootapi.board.model;


import kr.ac.daegu.springbootapi.comment.model.CommentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Getter
@Setter
public class BoardDTO {

    private int id;                      // 글 id(글번호)
    private String author;               // 작성자 이름
    private String subject;              // 글 제목
    private String content;              // 글 컨텐츠
    private LocalDate writeDate;              // 작성 날짜
    private LocalTime writeTime;              // 작성 시간
    private int readCount;               // 조회수
    private int commentCount;            // 댓글 갯수
    private String password;             // 수정 삭제를 위한 패스워드
    private int replyRootId;
    private int depth;
    private int orderNum;
    private String isDel;
    private List<CommentDTO> comments = null;


    @Override
    public String toString() {


            return "BoardDTO{" +
                    "id=" + id +
                    ", author='" + author + '\'' +
                    ", subject='" + subject + '\'' +
                    ", content='" + content + '\'' +
                    ", writeDate=" + writeDate +
                    ", writeTime=" + writeTime +
                    ", readCount=" + readCount +
                    ", commentCount=" + commentCount +
                    ", password='" + password + '\'' +
                    ", replyRootId=" + replyRootId +
                    ", depth=" + depth +
                    ", orderNum=" + orderNum +
                    ", comments=" + comments +
                    '}';


    }

}

