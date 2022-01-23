package kr.ac.daegu.springbootapi.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.function.Function;


@Slf4j
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler{

    @Override public void onLogoutSuccess(HttpServletRequest request,
                                          HttpServletResponse response,
                                          Authentication authentication) throws IOException, ServletException {
        log.debug("CustomLogoutSuccessHandler.onLogoutSuccess ::::");

        String requestTokenHeader = request.getHeader("Authorization");
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            String jwtToken = requestTokenHeader.substring(7);

            JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
            Date jwtTokenExp =jwtTokenUtil.getExpirationDateFromToken(jwtToken);
            log.debug("Exp Time :: " + jwtTokenExp);
        }
        // 토큰 exp 구해오는것 까지.
        // after 토큰 DB에 저장 후 인증필터에서 해당 토큰이 블랙리스트에 있는지 검사
        super.onLogoutSuccess(request, response, authentication);
    }
}

