package kr.ac.daegu.springbootapi.board.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
@Setter
public class CommentDTO {
           private int cid;
           private int id;
           private String author;
           private String content;
           private LocalDate writeDate;
           private LocalTime writeTime;

           @Override
           public String toString() {
               return "CommentDTO{" +
                       "id=" + id +
                       ", author='" + author + '\'' +
                       ", content='" + content + '\'' +
                       ", writeDate=" + writeDate +
                       ", writeTime=" + writeTime +
                       '}';
           }


}
