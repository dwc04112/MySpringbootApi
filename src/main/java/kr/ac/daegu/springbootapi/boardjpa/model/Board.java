package kr.ac.daegu.springbootapi.boardjpa.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "board")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Board {

    @Id
    @Column(name = "bid")
    private Integer bid;

    @Column(name = "mid")
    private Long mid;

    @Column(name = "author")
    private String author;
    @Column(name = "subject")
    private String subject;
    @Column(name = "content")
    private String content;

    @Column(name = "writeDate")
    private LocalDate writeDate;
    @Column(name = "writeTime")
    private LocalTime writeTime;
    @Column(name = "readCount")
    private Integer readCount;
    @Column(name = "commentCount")
    private Integer commentCount;
    @Column(name = "replyRootId")
    private Integer replyRootId;
    @Column(name = "depth")
    private Integer depth;
    @Column(name = "orderNum")
    private Integer orderNum;
    @Column(name = "isDel")
    private String isDel;

    public Board(Integer bid,
                 Long mid,
                 String author,
                 String subject,
                 String content,
                 LocalDate writeDate,
                 LocalTime writeTime,
                 Integer readCount,
                 Integer commentCount,
                 Integer replyRootId,
                 Integer depth,
                 Integer orderNum,
                 String isDel) {
        this.bid = bid;
        this.mid = mid;
        this.author = author;
        this.subject = subject;
        this.content = content;
        this.writeDate = writeDate;
        this.writeTime = writeTime;
        this.readCount = readCount;
        this.commentCount = commentCount;
        this.replyRootId = replyRootId;
        this.depth = depth;
        this.orderNum = orderNum;
        this.isDel = isDel;
    }
}