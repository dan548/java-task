package ge.sibraine.user_java_task;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Table(name = "authorities")
@IdClass(UserAuthorityKey.class)
@AllArgsConstructor
@Getter
public class UserAuthority {

    @Id
    private String user_id;

    @Id
    private String name;

}
