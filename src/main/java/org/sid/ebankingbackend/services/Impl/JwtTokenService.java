package org.sid.ebankingbackend.services.Impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class JwtTokenService {
    private static final int tokenExpirationTime = 30 * 60 * 1000;
    private static final String tokenKey = "ut1FfO9sSPjG1OKxVh";

    public static String generateToken(String username, Claims claims) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpirationTime))
                .signWith(SignatureAlgorithm.HS512, tokenKey)
                .compact();
    }

    public static void verifyToken(String token) throws JwtException {
        Jwts.parser()
                .setSigningKey(tokenKey)
                .parse(token.substring(7));
    }

    public static Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(tokenKey)
                .parseClaimsJws(token.substring(7))
                .getBody();
    }

    public static String updateExpirationDateToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpirationTime))
                .signWith(SignatureAlgorithm.HS512, tokenKey)
                .compact();
    }
}
