package ge.sibraine.user_java_task.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Component
public class JwtSettings {

    private String tokenIssuer;
    private String tokenSigningKey;
    private int aTokenDuration;
    private String jwtCookie;

    public JwtSettings(@Value("${jwt.issuer}") final String tokenIssuer,
                       @Value("${jwt.signingKey}") final String tokenSigningKey,
                       @Value("${jwt.aTokenDuration}") final int aTokenDuration,
                       @Value("${jwt.cookieName}") final String jwtCookie) {
        this.tokenIssuer = tokenIssuer;
        this.tokenSigningKey = tokenSigningKey;
        this.aTokenDuration = aTokenDuration;
        this.jwtCookie = jwtCookie;
    }

    String getTokenIssuer() {
        return tokenIssuer;
    }

    byte[] getTokenSigningKey() {
        return tokenSigningKey.getBytes(StandardCharsets.UTF_8);
    }

    Duration getTokenExpiredIn() {
        return Duration.ofMinutes(aTokenDuration);
    }

    String getJwtCookie() {
        return jwtCookie;
    }
}
