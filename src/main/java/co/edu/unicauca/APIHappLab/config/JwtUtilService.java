package co.edu.unicauca.APIHappLab.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtUtilService {
	// alguna.clave.secreta.12345678->base64->YWxndW5hLmNsYXZlLnNlY3JldGEuMTIzNDU2Nzg=
	private static final String JWT_SECRET_KEY = "YWxndW5hLmNsYXZlLnNlY3JldGEuMTIzNDU2Nzg=";

	public static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 60 * (long) 8; // 8 Horas

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		return claimsResolver.apply(extractAllClaims(token));
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		// Agregando informacion adicional como "claim"
		var rol = userDetails.getAuthorities().stream().collect(Collectors.toList()).get(0);
		claims.put("rol", rol);
		return createToken(claims, userDetails.getUsername());
	}

	public String generateTokenWithContent(UserDetails userDetails,Object content) {
		Map<String, Object> claims = new HashMap<>();
		ObjectMapper mapper=new ObjectMapper();
		claims = mapper.convertValue(content, HashMap.class);

		return createToken(claims, userDetails.getUsername());
	}
	
	private String createToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
				.signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY).compact();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}