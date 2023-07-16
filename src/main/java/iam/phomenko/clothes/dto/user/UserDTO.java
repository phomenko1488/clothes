package iam.phomenko.clothes.dto.user;

import iam.phomenko.clothes.domain.users.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;

    public UserDTO(User user){
        this.username=user.getUsername();
    }
}
