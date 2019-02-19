package com.bridgelabz.fundoonote.noteutil;

public interface TokenGeneratorInf {
	
	String generateToken(String id);

	int authenticateToken(String token);
}
