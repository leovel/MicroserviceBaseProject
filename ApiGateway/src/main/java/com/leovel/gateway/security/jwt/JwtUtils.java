package com.leovel.gateway.security.jwt;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.leovel.gateway.common.models.UserDTO;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
  
//creates a spec-compliant secure-random key:
  private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512); //or HS384 or HS256

  @Value("${api.gateway.jwtExpirationMs}")
  private int jwtExpirationMs;
  
  //retrieve username from jwt token
  public String getUserNameFromJwtToken(String token) {
      return getClaimFromJwtToken(token, Claims::getSubject);
  }

  //retrieve expiration date from jwt token
  public Date getExpirationDateFromJwtToken(String token) {
      return getClaimFromJwtToken(token, Claims::getExpiration);
  }

  public <T> T getClaimFromJwtToken(String token, Function<Claims, T> claimsResolver) {
      final Claims claims = getAllClaimsFromToken(token);
      return claimsResolver.apply(claims);
  }


  //for retrieving any information from token we will need the secret key
  public Claims getAllClaimsFromToken(String token) {
      return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
  }

  public String generateJwtToken(UserDTO user) {
    
    Map<String, Object> claims = new HashMap<>();
    claims.put("role", Arrays.asList(user.getRole()));

    return Jwts.builder().setClaims(claims).setSubject((user.getUsername())).setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
        .signWith(key, SignatureAlgorithm.HS512)
        .compact();
  }

  public boolean validateJwtToken(String authToken) {
    try {
    	Jwts.parserBuilder()
    	.setSigningKey(key)
    	.build()
    	.parseClaimsJws(authToken);
      return true;
    } catch (SecurityException e) {
      logger.error("Invalid JWT signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }
}
