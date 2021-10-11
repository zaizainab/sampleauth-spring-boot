package com.ecomindo.onboarding.sampleauth.util;

import java.io.File;
import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

import com.ecomindo.onboarding.sampleauth.dto.CustomUserDTO;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

// import api.auth.dto.CustomUserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil {

	@Value("${spring.jwt.issuer}")
	private String jwtIssuer;

	// @Autowired
	// private Logger logger;	
	
	public String generateAccessToken(CustomUserDTO user) throws Exception {
		Claims claim = Jwts.claims();
		claim.put("fullname", user.getFullname());
        claim.put("username", user.getUsername());
		claim.put("roles", user.getAuthorities().stream().map(x -> x.getAuthority()).collect(Collectors.toList()));

		return Jwts.builder()
				.setClaims(claim)
				.setSubject(user.getUsername())
				.setIssuer(jwtIssuer)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1 * 24 * 60 * 60 * 1000))
				.signWith(SignatureAlgorithm.RS256, getKey())
				.compact();
	}

	public String getUserId(String token) {
		Claims claims = parseToken(token).getBody();
		return claims.getSubject().split(",")[0];
	}

	public String getUsername(String token) {
		Claims claims = parseToken(token).getBody();
		return claims.getSubject().split(",")[1];
	}

	public Date getExpirationDate(String token) {
		Claims claims = parseToken(token).getBody();
		return claims.getExpiration();
	}

	public boolean validate(String token) {
		try {
			parseToken(token);
			return true;
		} catch (SignatureException ex) {
            System.out.println("Invalid JWT signature - "+ ex.getMessage());
		} catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token - "+ ex.getMessage());
		} catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token - "+ ex.getMessage());
		} catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token - "+ ex.getMessage());
		} catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty - "+ ex.getMessage());
		}
		return false;
	}
	
	private Jws<Claims> parseToken(String token) {
		return Jwts.parser().setSigningKey(getKey()).parseClaimsJws(token);
	}
	
	private Key getKey() {
		try {
			File file = new ClassPathResource("private.der").getFile();
			Key privatekey = KeyReader.readPrivateKey(file);
			return privatekey;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}