package kr.ac.daegu.springbootapi.test.controller;


import kr.ac.daegu.springbootapi.test.model.TestDTO;
import kr.ac.daegu.springbootapi.test.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/test")
@RequiredArgsConstructor
@Slf4j
public class TestController {

    public final TestService boardService;

    @GetMapping(value = "/")
    public List<TestDTO> rootTest() throws Exception {
        log.trace("logTest~~~~~~~~~~~~~~~~~~~~~~~~~~~~"); // 로그의 5단계 trace(일반적으로 남기는 이벤트 기록) -> debug(디버깅용) -> info(알림) -> warn(경고) -> error(에러)
        log.debug("logTest~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        log.info("logTest~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        log.warn("logTest~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        log.error("logTest~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


        List<TestDTO> testList = boardService.getTestList();

        return testList;

    }
}