package kr.ac.daegu.springbootapi.member;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = true)
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String nickName;

    public Member(String email, String password, String firstName, String lastName, String nickName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
    }

    public static Member createMember(String email, String password, String firstName,String lastName, String nickName){
        return new Member(email, password, firstName, lastName, nickName);
    }
}
