package iam.phomenko.clothes.dto.user;

import iam.phomenko.clothes.domain.users.User;
import iam.phomenko.clothes.dto.collection.CollectionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private List<CollectionDTO> collections;

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.collections = user.getCollections()
                .stream()
                .map(CollectionDTO::new)
                .collect(Collectors.toList());
    }
}
