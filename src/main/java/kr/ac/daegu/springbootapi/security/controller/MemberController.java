package kr.ac.daegu.springbootapi.security.controller;

import kr.ac.daegu.springbootapi.member.Member;
import kr.ac.daegu.springbootapi.member.MemberRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    final MemberRepository memberRepository;
    final PasswordEncoder passwordEncoder;

    @PostMapping("/api/member")
    public String saveMember(@RequestBody MemberDto memberDto){
        memberRepository.save(Member.createMember(memberDto.getEmail(),
                passwordEncoder.encode(memberDto.getPassword()) , memberDto.getName(), memberDto.getNickname()));
        return "success";
    }

    @PostMapping("signup/doublecheck")
    public Integer doubleCheck(@RequestBody MemberDto memberDto){
        String nick = memberDto.getNickname();
        return check(nick);
        //중복확인
    }

    public Integer check(String nick){
        return memberRepository.getMemberByNickname(nick);
    }
}

@Data
class MemberDto{
    private String email;
    private String password;
    private String name;
    private String nickname;
}