package com.pj.untapped.security.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pj.untapped.dtos.security.TokenDTO;
import com.pj.untapped.service.exceptions.InvalidJwtAuthenticationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JwtTokenProvider {
    
    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";
    
    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000; //1 Hora
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    Algorithm algorithm = null;
    
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }
    
    public TokenDTO createAccessToken(String username, List<String> roles) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds); //Calculando o tempo para expirar o token
        var accessToken = getAccessToken(username, roles, now, validity);
        var refreshToken = getRefreshToken(username, roles, now);
        return new TokenDTO(username, true, now, validity, accessToken, refreshToken);
    }

    private String getAccessToken(String username, List<String> roles, Date now, Date validity) {
        String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return JWT.create()
                    .withClaim("roles", roles)
                    .withIssuedAt(now)
                    .withExpiresAt(validity)
                    .withSubject(username)
                    .withIssuer(issuerUrl)
                    .sign(algorithm)
                    .strip();
    }

    private String getRefreshToken(String username, List<String> roles, Date now) {
        Date validityRefreshToken = new Date(now.getTime() + (validityInMilliseconds * 3));
        return JWT.create()
                    .withClaim("roles", roles)
                    .withIssuedAt(now)
                    .withExpiresAt(validityRefreshToken)
                    .withSubject(username)
                    .sign(algorithm)
                    .strip();
    }
    
    public Authentication getAuthentication(String token) {
        DecodedJWT decodeJWT = decodedToken(token);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(decodeJWT.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private DecodedJWT decodedToken(String token) {
        Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
        JWTVerifier verifier = JWT.require(alg).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT;
    }
    
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length()); //Retorna um subString do token (desconsiderando o Bearer contido na string)
        }
        return null;
    }
    
    public boolean validadeToken(String token) {
        DecodedJWT decodedJWT = decodedToken(token);
        try {
            if(decodedJWT.getExpiresAt().before(new Date())) { //Se for antes desse momento, o token está expirado
                return false;
            }
            return true;
        } catch (Exception e) {
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
        }
    }
}






















