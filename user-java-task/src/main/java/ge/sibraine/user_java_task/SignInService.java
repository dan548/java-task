package ge.sibraine.user_java_task;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignInService {

    private final UserRepository users;
    private final PasswordEncoder passwordEncoder;

    public SignInService(UserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(final SignInRequest signInRequest) {
        User user = users.findByUsername(signInRequest.username())
                .orElseThrow(() -> new LoginFailedException(
                        "User '" + signInRequest.username() + "' not found"
                ));

        if (!passwordEncoder.matches(signInRequest.password(), user.getPassword())) {
            throw new LoginFailedException("Wrong password");
        }

        return user;
    }
}
