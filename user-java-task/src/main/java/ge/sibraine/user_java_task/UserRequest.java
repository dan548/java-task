package ge.sibraine.user_java_task;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    @NotBlank(message = "Username cannot be blank.")
    private String username;

    @Email(message = "Email should be valid.")
    private String email;

    @Size(min = 8, message = "Password must be 8 characters or longer.")
    private String password;

}
