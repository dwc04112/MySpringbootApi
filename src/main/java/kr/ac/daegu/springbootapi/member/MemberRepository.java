package kr.ac.daegu.springbootapi.member;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    @Query("SELECT count (m.nickname) FROM Member m where m.nickname=?1")
    Integer getMemberByNickname(String nickname);


}
