package ge.sibraine.user_java_task;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class SignUpRequest {

    @NotBlank(message = "Username cannot be blank.")
    private final String username;

    @Email(message = "Email should be valid.")
    private final String email;

    @Size(min = 8, message = "Password must be 8 characters or longer.")
    private final String password;

    public SignUpRequest(final String username, final String password) {
        this.username = username;
        this.password = password;
        this.email = null;
    }

}
