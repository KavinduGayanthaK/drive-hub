package lk.ijse.driveHub.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UserDto {
    private int id;
    private String userName;
    private String password;
    private String email;

    public UserDto(String username, String password) {
        this.userName = username;
        this.password = password;
    }
}
