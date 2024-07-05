package ge.sibraine.user_java_task.security;

import ge.sibraine.user_java_task.User;
import org.springframework.security.core.Authentication;

import java.time.Duration;

public interface JwtService {

    Duration getTokenExpiredIn();
    String generateToken(User user);
    Authentication parseToken(String token);

}
