package kr.ac.daegu.springbootapi.token;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token {

    @Id
    private long tokenId;
    private String token;
}
