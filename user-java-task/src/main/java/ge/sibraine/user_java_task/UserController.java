package ge.sibraine.user_java_task;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserDetails(@PathVariable String id) {
        return userService.getUserDetails(id);
    }

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

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User editUser(@PathVariable String id, @RequestBody @Valid UserRequest userDetails) {
        return userService.editUser(id, userDetails);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> removeUser(@PathVariable String id) {
        return userService.removeUser(id);
    }

}
