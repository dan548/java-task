package ge.sibraine.user_java_task;

import org.springframework.security.core.AuthenticationException;

class LoginFailedException extends AuthenticationException {

    LoginFailedException(final String message) {
        super(message);
    }

}
