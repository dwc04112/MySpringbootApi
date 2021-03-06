package kr.ac.daegu.springbootapi.member;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    @Query("select m.mid, m.firstName, m.lastName, m.nickName FROM Member m where m.email=?1")
    List<Object[]> findMemberByEmail(String email);


    @Query("SELECT count (m.nickName) FROM Member m where m.nickName=?1")
    Integer getMemberByNickname(String nickName);

    @Transactional
    @Modifying
    @Query("update Member m set m.nickName = ?1 where m.mid=?2")
    void EditMemberNickName(String nickName, Long id);

    @Transactional
    @Modifying
    @Query("update Member m set m.firstName = ?1, m.lastName= ?2  where m.mid=?3")
    void EditMemberName(String firstName,String lastName, Long id);
}
