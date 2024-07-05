package ge.sibraine.user_java_task;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
@RequestMapping("/api/signup")
public class SignUpController {

    private UserService userService;

    @PostMapping
    @ResponseBody
    public ResponseEntity signUpUser(final @RequestBody SignUpRequest request) {
        UserServiceResponse response = userService.createUser(request);
        return switch (response.code()) {
            case SUCCESS -> ResponseEntity.noContent().build();
            case EXISTS -> ResponseEntity.status(HttpStatus.CONFLICT).build();
            default -> ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        };
    }

}
