package io.github.msouza28.clinicamedicaapi.auth;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import io.github.msouza28.clinicamedicaapi.entity.Usuario;

@Service
public class TokenService {
	
	@Value("${api.token.secret}")
	private String secret;
	
	public String gerarToken(Usuario usuario) {
		try {
		    var algorithm = Algorithm.HMAC256(secret);
		    return JWT.create()
		        .withIssuer("Clinica-Medica-API")
		        .withSubject(usuario.getLogin())
		        .withExpiresAt(dataExpiracao())
		        .sign(algorithm);
		} catch (JWTCreationException exception){
		    throw new RuntimeException("O token nao pôde ser gerado", exception);
		}
	}
	
	public String getSubject(String tokenJWT) {
		try {
		    var algorithm = Algorithm.HMAC256(secret);
		    return JWT.require(algorithm)
		    		.withIssuer("Clinica-Medica-API")
		    		.build()
		    		.verify(tokenJWT.trim())
		    		.getSubject();
		} catch (JWTCreationException exception){
		    throw new RuntimeException("Token JWT invalido ou expirado", exception);
		}
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

}
