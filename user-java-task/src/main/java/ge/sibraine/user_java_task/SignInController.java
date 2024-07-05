package ge.sibraine.user_java_task;

import ge.sibraine.user_java_task.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/signin")
@AllArgsConstructor
public class SignInController {

    private final SignInService signInService;
    private final JwtService jwtService;

    @PostMapping
    @ResponseBody
    public SignInResponse create(final @RequestBody SignInRequest signInRequest) {
        User user = signInService.login(signInRequest);
        String token = jwtService.generateToken(user);
        return new SignInResponse(token);
    }
}
