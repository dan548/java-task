package ge.sibraine.user_java_task.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HeaderJwtAuthFilter extends JwtAuthFilter {

    private static final Pattern BEARER_AUTH_PATTERN = Pattern.compile("^Bearer\\s+(.*)$");
    private static final int TOKEN_GROUP = 1;

    public HeaderJwtAuthFilter(final RequestMatcher matcher) {
        super(matcher);
    }

    @Override
    protected String takeToken(final HttpServletRequest request) throws AuthenticationException {
        String authHeader = request.getHeader("Authorization");
        Matcher m = BEARER_AUTH_PATTERN.matcher(authHeader);
        if (m.matches()) {
            return m.group(TOKEN_GROUP);
        } else {
            throw new JwtAuthenticationException("Invalid Authorization header: " + authHeader);
        }
    }

}
