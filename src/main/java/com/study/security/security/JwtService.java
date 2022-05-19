package com.study.security.security;

import com.study.security.model.UserSale;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


// Token encode and decode
@Service
public class JwtService {

    @Value("${security.jwt.expiration}")
    private String expiration;

    @Value("${security.jwt.key}")
    private String key;

    public String getToken(UserSale userSale) {
        long exp = Long.valueOf(expiration);
        LocalDateTime dateTimeExp = LocalDateTime.now().plusMinutes(exp);
        Date dateExp = Date.from(dateTimeExp.atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .setSubject(userSale.getLogin())
                .setExpiration(dateExp)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = getClaims(token);
            Date dateExpiration = claims.getExpiration();
            LocalDateTime localDateTimeExp = dateExpiration.toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDateTime();

            return LocalDateTime.now().isAfter(localDateTimeExp);
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserSalesLogin(String token) {
        return (String) getClaims(token).getSubject();
    }

    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

}
