package com.evin.tms.authservice.service;

import com.evin.tms.authservice.dto.LoginDto;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Utility service for handling JWT (JSON Web Token) operations.
 * This includes creating tokens, extracting information from tokens, and validating tokens.
 */
@Service
public class JwtUtil {

    /**
     * Secret key used for signing the JWTs.
     * This should be kept secure and not exposed publicly.
     */
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    /**
     * Extracts the username (subject) from the JWT.
     *
     * @param token JWT from which the username is to be extracted.
     * @return Extracted username.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts the expiration date from the JWT.
     *
     * @param token JWT from which the expiration date is to be extracted.
     * @return Extracted expiration date.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Generic method to extract a specific claim from the token.
     *
     * @param token JWT from which the claim is to be extracted.
     * @param claimsResolver Function to extract a specific claim.
     * @return Extracted claim.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extracts all claims from the JWT.
     *
     * @param token JWT to extract claims from.
     * @return Extracted claims.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    /**
     * Checks if the JWT has expired.
     *
     * @param token JWT to check for expiration.
     * @return true if token has expired, false otherwise.
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Generates a JWT token for a user.
     *
     * @param userDetails UserDetails object containing the username.
     * @return Generated JWT token.
     */
    public String generateToken(LoginDto userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    /**
     * Creates the JWT token with specified claims and subject.
     *
     * @param claims Claims to be included in the token.
     * @param subject Subject for whom the token is being created.
     * @return Created JWT token.
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // 10 hours
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    /**
     * Validates a JWT token against user details.
     *
     * @param token JWT token to be validated.
     * @param userDetails UserDetails against which to validate the token.
     * @return true if token is valid, false otherwise.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
