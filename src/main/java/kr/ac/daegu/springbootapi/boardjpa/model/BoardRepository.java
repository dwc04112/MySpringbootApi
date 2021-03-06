package kr.ac.daegu.springbootapi.boardjpa.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    List<Board> findAll();
    List<Board> findBoardByIsDel(String isDel);
    List<Board> findBoardByBidAndIsDel(int bid ,String isDel);

    Page<Board> findBoardsByIsDel(String isDel, Pageable pageable);
    Page<Board> findBoardsByIsDelOrderByReplyRootIdDescOrderNumAsc(String isDel, Pageable pageable);

    Page<Board> findBoardByIsDelAndAuthorLikeOrderByReplyRootIdDescOrderNumAsc(String isDel, String svalue, Pageable pageable);

    Page<Board> findBoardByIsDelAndSubjectLikeOrderByReplyRootIdDescOrderNumAsc(String isDel, String svalue, Pageable pageable);

    Page<Board> findBoardByIsDelAndContentLikeOrderByReplyRootIdDescOrderNumAsc(String isDel, String svalue, Pageable pageable);


    Optional<Board> findBoardByBid(int bId);





    // JPQL != SQL
    @Query("Select b FROM Board b where b.bid = ?1") // JPA를 이용하여 JPQL만든 후 쿼리를 날린다.
    Board selectBoard(int bId);

    @Query("select b.mid From Board b where b.bid = ?1")
    Long getMemberId(int bId);


    @Query("select b.readCount from Board b where b.bid=?1")
    Integer getReadCount(int bid);


    @Modifying
    @Query("UPDATE Board SET orderNum = orderNum + 1" +
            "                WHERE replyRootId = ?1  AND orderNum >= ?2")
    void updateOrderNum(int replyRootId, int minOrderNum);
    // 최고 Id값을 가진 Board 엔터티를 가져옴.

    Board findTopByOrderByBidDesc();

    @Transactional
    @Modifying
    @Query("UPDATE Board SET readCount = readCount + 1 where bid = ?1")
    void updateReadCount(int bid);


    /*
    @Query("SELECT MIN(b.orderNum) FROM Board b" +
            "                WHERE  b.replyRootId = ?1" +
            "                AND b.orderNum > ?3" +
            "                AND b.depth <= ?2")
    Integer getMinOrderNum(int replyRootId, int depth, int orderNum);

    @Query("SELECT MAX(orderNum) + 1 FROM Board" +
            " WHERE replyRootId = ?1")
    Integer getReplyOrderNum(int replyRootId);




     */

}