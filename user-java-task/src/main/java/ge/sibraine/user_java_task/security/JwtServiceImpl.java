package ge.sibraine.user_java_task.security;

import ge.sibraine.user_java_task.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceImpl implements JwtService {

    private final JwtSettings settings;

    @Override
    public String generateToken(User user) {
        log.debug("Generating token for {}", user.getUsername());

        Instant now = Instant.now();

        SecretKey key = Keys.hmacShaKeyFor(settings.getTokenSigningKey());

        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(settings.getTokenExpiredIn())))
                .claim("authorities", user.getAuthorities())
                .signWith(key, Jwts.SIG.HS512)
                .compact();
    }

    @Override
    public Duration getTokenExpiredIn() {
        return settings.getTokenExpiredIn();
    }

    @Override
    public Authentication parseToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(settings.getTokenSigningKey()))
                .build()
                .parseSignedClaims(token)
                .getPayload();

        String id = claims.getSubject();
        List<String> tokenAuthorities = claims.get("authorities", List.class);

        List<GrantedAuthority> authorities = tokenAuthorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new AuthenticatedJwtToken(id, authorities);
    }
}
