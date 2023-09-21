package com.pmh.trainingspring.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.pmh.trainingspring.detail.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	private static final String SECRET_KEY = "12345678912345678912346789123465791235464898944645656456478998123132311323212313213456789789";
    private static final long EXPIRE_TIME = 86400000000L;
    
    public String generateJwtToken(Authentication authentication) {
    	
    	UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
    	
    	return Jwts.builder()
    			.setSubject((userPrincipal.getUsername()))
    			.setIssuedAt(new Date())
    			.setExpiration(new Date((new Date().getTime() + EXPIRE_TIME * 1000)))
    			.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
        		.compact();
    }
    
    public String getUserNameFromJwtToken(String token) {
    	return Jwts.parser()
    		.setSigningKey(SECRET_KEY)
    		.parseClaimsJws(token)
    		.getBody().getSubject();
    }
    
    public boolean validateJwtToken(String authToken) {
    	try {
			Jwts.parser().setSigningKey(SECRET_KEY).parse(authToken);
			return true;
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
