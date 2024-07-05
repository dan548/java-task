package ge.sibraine.user_java_task.security;

import org.springframework.security.core.AuthenticationException;

class JwtAuthenticationException extends AuthenticationException {


    JwtAuthenticationException(final String message) {
        super(message);
    }
    JwtAuthenticationException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
