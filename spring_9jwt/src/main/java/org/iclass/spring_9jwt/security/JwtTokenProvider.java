package org.iclass.spring_9jwt.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenProvider {
    // 토큰 만드는 비밀키
    private static final String SECRET_KEY = "756be4cf9581add13ddb3ab3e2f1e75f27a0661af1c1225a89ef9a1d44d3f03b";
    // 유효시간
    private int jwtExpirationInMs = 24 * 60 * 60 * 1000; // 1일을 ms 단위로 계산

    public String createToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        Date expiryDate = new Date(System.currentTimeMillis() + jwtExpirationInMs);

        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());   // 전자서명에 사용할 해시값 생성
        return Jwts.builder()
                .signWith(secretKey) // 아래의 정보들을 개인키로 암호화한 전자서명 생성
                .subject(userPrincipal.getUsername()) // 여기서부터 토큰과 관련된 정보 저장
                .issuer("org.iclass")   // 발급자:서비스이름
                .issuedAt(new Date())    // 발급날짜시간
                .expiration(expiryDate)  // 만료날짜시간
                .compact();
    }

    // 클라이언트가 보낸 토큰(메소드 인자 String token)을 검증하는 메소드
    public String getUsernameFromToken(String token) {

        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        Claims claims = Jwts.parser()
                .verifyWith(secretKey).build().parseSignedClaims(token).getPayload();

        // subject 는 username를 저장했으므로 토큰 값을 분해해서 얻은 subject 는 username 이다.
        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty");
        }
        return false;
    }
}
