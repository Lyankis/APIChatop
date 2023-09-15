package com.openclassrooms.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService{
	
	private static final String SECRET_KEY = "lriAc1p6y27mg+Cy5TpDBh3USQ2CnFedOjp2kj9PE/ICFr6maDoahCPG2kGnsAFWhLANySppkBbHJxPyoIdchPXpP2WMHERZNY5LBCmmMj8B7N7TulyFTjLM72TC9JCGZe0ToYfIe5QNTeqzjU0wR1F2jhHdFBRrU7wikMEYoFoP7Mx8uJRxjotW4x2ngcM0XMKnoK6TgGyQ8IdoUI359Ain0BbrhrQXlZP5VeoPeXfrdz0UyeUQqTTwAok0zs4g4mBfxYm+W+2L9uc8H/mqkMfMcLjDzm+dLxxZRRlGdY3y3ctOpmvbuoTbCwlQ3rtlT1EYo2eg6cyn0//8RCKkjvmjVr2xsXNqSlr6aIxP66I=";

//	@Value("${token.signing.key}")
//    private String jwtSigningKey;
    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
