package warehouse.domains;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class User {
    private Long id;
    private String username;
    private String password;
    private String fullName;
    private String phoneNumber;
    private UserRole role;

    @Builder(builderMethodName = "childBuilder")
    public User(Long id, String username, String password, String fullName, String phoneNumber, UserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public User(Long id, String username, String password, UserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
