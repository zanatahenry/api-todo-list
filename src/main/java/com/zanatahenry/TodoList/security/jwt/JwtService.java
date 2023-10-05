package com.zanatahenry.TodoList.security.jwt;

import com.zanatahenry.TodoList.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {
  @Value("${security.jwt.TOKEN-EXPIRATION-HOURS}")
  private String expirationTime;


  private String privateKey = "d/HZBgGxRplJYoXMdHft0w==";

  private SecretKey key = Jwts.SIG.HS256.key().build();
  public String getToken (UserEntity user) {
    long expString = Long.valueOf(this.expirationTime);
    LocalDateTime expiresIn = LocalDateTime.now().plusHours(expString);
    Instant instant = expiresIn.atZone(ZoneId.systemDefault()).toInstant();
    Date date = Date.from(instant);

    return Jwts
        .builder()
        .subject(user.getEmail())
        .expiration(date)
        .signWith(key)
        .compact();
  }

  private Claims getClaims (String token) throws ExpiredJwtException {
    return Jwts
        .parser()
        .verifyWith(this.key)
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  public boolean isValid (String token) {
    try {
      Claims claims = this.getClaims(token);
      Date expiresIn = claims.getExpiration();
      LocalDateTime date = expiresIn.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
      return !LocalDateTime.now().isAfter(date);
    } catch (Exception e) {
      return false;
    }
  }

  public String getUserEmail (String token) throws ExpiredJwtException {
    return (String) this.getClaims(token).getSubject();
  }
}
