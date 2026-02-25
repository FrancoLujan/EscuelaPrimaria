package com.example.EscuelaPrimaria.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.AlgorithmConstraints;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    @Value("{security.jwt.private.key}")
    private String privateKey;

    @Value("{security.jwt.user.generator}")
    private String userGenerator;


    public String createToken(Authentication auth) {
        Algorithm algorithm = Algorithm.HMAC256(privateKey);

        String username = auth.getPrincipal().toString();

        String authorities = auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        String token = JWT.create()
                .withIssuer(userGenerator)
                .withSubject(username)
                .withClaim("autorities", authorities) // CUIDADO COMO ESCRIBIS EL CLAIM
                .withIssuedAt(new Date())
                .withExpiresAt((new Date(System.currentTimeMillis() + 3600000))) // en una hora vence
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);

        return token;
    }

    public DecodedJWT validateToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(privateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(userGenerator)
                    .build();
            return verifier.verify(token);

        }catch (JWTVerificationException e){
            throw new JWTVerificationException("Token invalido AAAAAAAAAAAA");
        }

    }
    public String extractUsername(DecodedJWT deco) {
        return deco.getSubject().toString();
    }

    public Claim getClaim(DecodedJWT deco, String claimName) {
        return deco.getClaim(claimName);
    }

    public Map<String , Claim> allClaim(DecodedJWT deco) {
        return deco.getClaims();
    }


}
