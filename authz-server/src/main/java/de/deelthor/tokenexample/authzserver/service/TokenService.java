package de.deelthor.tokenexample.authzserver.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import de.deelthor.tokenexample.authzserver.TokenProperties;
import de.deelthor.tokenexample.authzserver.repository.Role;
import de.deelthor.tokenexample.authzserver.repository.User;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class TokenService {

    private TokenProperties properties;
    private Algorithm algorithm;
    private JWTVerifier verifier;

    public TokenService(TokenProperties properties) throws UnsupportedEncodingException {
        this.properties = properties;
        this.algorithm = Algorithm.HMAC256(properties.getSecret());
        this.verifier = JWT.require(algorithm).acceptExpiresAt(0).build();
    }

    public String encode(User user) {
        LocalDateTime now = LocalDateTime.now();
        try {
            return JWT.create()
                    .withIssuer(properties.getIssuer())
                    .withSubject(user.getEmail())
                    .withIssuedAt(Date
                            .from(now.atZone(ZoneId.systemDefault())
                                    .toInstant()))
                    .withExpiresAt(Date
                            .from(now.plusSeconds(properties.getMaxAgeSeconds())
                                    .atZone(ZoneId.systemDefault())
                                    .toInstant()))
                    .withArrayClaim("role", user
                            .getRoles()
                            .stream()
                            .map(Role::getRole)
                            .toArray(String[]::new))
                    .sign(algorithm);
        } catch (JWTCreationException ex) {
            throw new TokenCreationException("Cannot properly create token", ex);
        }
    }

    public DecodedJWT decode(String token) {
        return this.verifier.verify(token);
    }
}
