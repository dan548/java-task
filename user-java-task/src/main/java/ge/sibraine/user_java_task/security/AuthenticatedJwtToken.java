package ge.sibraine.user_java_task.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

class AuthenticatedJwtToken extends AbstractAuthenticationToken {

    private final String id;

    AuthenticatedJwtToken(final String id, final Collection<GrantedAuthority> authorities) {
        super(authorities);
        this.id = id;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return id;
    }

}
