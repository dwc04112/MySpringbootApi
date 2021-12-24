package kr.ac.daegu.springbootapi.member;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    @Query("SELECT count (m.nickName) FROM Member m where m.nickName=?1")
    Integer getMemberByNickname(String nickName);

    @Query("select m.firstName, m.lastName, m.nickName from Member m where m.email =?1")
    String getMemberByEmail(String email);
}
