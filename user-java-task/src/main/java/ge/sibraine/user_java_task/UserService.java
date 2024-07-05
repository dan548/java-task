package ge.sibraine.user_java_task;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> getAllUsers();
    User getUserDetails(String userId);
    UserServiceResponse createUser(SignUpRequest userRequest);
    User editUser(String userId, UserRequest userDetails);
    Map<String, Boolean> removeUser(String userId);

}
