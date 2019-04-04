package com.bridgelabz.fundoonote.noteutil;


import java.util.Date;
import javax.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenGeneratorImpl implements TokenGeneratorInf {

	public String generateToken(String id) {
		return Jwts.builder().setId(id).claim("roles", "existingUser").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretKey").compact();
	}

	public int authenticateToken(String token)
	{
		Claims claims = Jwts.parser()        
				.setSigningKey(DatatypeConverter.parseBase64Binary("secretKey"))
				.parseClaimsJws(token).getBody();
		System.out.println("ID: " + claims.getId());
		return Integer.parseInt(claims.getId());
	}


}


