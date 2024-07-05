package ge.sibraine.user_java_task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    private String id;

    private String username;

    private String email;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private List<UserAuthority> authorities;

    @JsonIgnore
    private String password;

    public User(String id, String username, String email, List<String> authorities) {
        this.id = id;
        this.username = username;
        this.authorities = authorities.stream().map(
                authority -> new UserAuthority(id, authority)
        ).toList();
        this.email = email;
        this.password = null;
    }

    public User(final Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
            id = null;
        } else {
            id = principal.toString();
            username = null;
        }
        password = null;
        authorities = new ArrayList<>();
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            authorities.add(new UserAuthority(id, authority.getAuthority()));
        }
    }
}
