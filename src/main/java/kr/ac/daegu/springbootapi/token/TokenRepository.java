package kr.ac.daegu.springbootapi.token;
import org.hibernate.sql.Insert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
public interface TokenRepository extends JpaRepository<Token, String> {

    @Query("select count(t.token) FROM Token t where t.token = ?1")
    Integer compareToken(String jwtToken);
}
