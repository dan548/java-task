package ge.sibraine.user_java_task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserDetails(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
    }

    @Override
    public UserServiceResponse createUser(SignUpRequest userRequest) {
        if (userRequest != null && userRequest.getUsername() != null && userRequest.getPassword() != null &&
                !userRequest.getUsername().isEmpty() && !userRequest.getPassword().isEmpty()) {
            if (userRepository.findByUsername(userRequest.getUsername()).isEmpty()) {
                String password = passwordEncoder.encode(userRequest.getPassword());
                String id =  UUID.randomUUID().toString();
                User user = User.builder()
                        .password(password)
                        .username(userRequest.getUsername())
                        .id(id)
                        .build();
                userRepository.save(user);
            } else {
                return new UserServiceResponse(UserResponseCode.EXISTS);
            }
        }
        return new UserServiceResponse(UserResponseCode.SUCCESS);
    }

    @Override
    public User editUser(String userId, UserRequest userDetails) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        user.setPassword(userDetails.getPassword());
        user.setUsername(userDetails.getUsername());
        return userRepository.save(user);
    }

    @Override
    public Map<String, Boolean> removeUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        userRepository.delete(user);
        return Map.of("deleted", Boolean.TRUE);
    }

}
