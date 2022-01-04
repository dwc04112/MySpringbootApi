package kr.ac.daegu.springbootapi.security.controller;

import kr.ac.daegu.springbootapi.member.Member;
import kr.ac.daegu.springbootapi.member.MemberRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    final MemberRepository memberRepository;
    final PasswordEncoder passwordEncoder;

    @PostMapping("/api/member")
    public String saveMember(@RequestBody MemberDto memberDto){
        memberRepository.save(Member.createMember(memberDto.getEmail(),
                passwordEncoder.encode(memberDto.getPassword()) , memberDto.getFirstName(), memberDto.getLastName(), memberDto.getNickName()));
        return "success";
    }

    @PostMapping("/signup/doublecheck")
    public Integer doubleCheck(@RequestBody MemberDto memberDto){
        log.debug("닉네임? : "+ memberDto.getNickName());
        String nick = memberDto.getNickName();
        return check(nick);
        //중복확인
    }
    public Integer check(String nick){
        return memberRepository.getMemberByNickname(nick);
    }

    //1224 수정필요

    @PostMapping("/user/info")
    public Map<String, String> getUserInfo(@RequestBody MemberDto memberDto){
        String email = memberDto.getEmail();
        log.debug("UserInfo in : " + email);
        Member UserInfo = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        Map<String,String> UserData = new HashMap<String, String>();
        UserData.put("nickName",UserInfo.getNickName());
        UserData.put("firstName",UserInfo.getFirstName());
        UserData.put("lastName",UserInfo.getLastName());
        return UserData;
    }

}

@Data
class MemberDto{
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String nickName;
}