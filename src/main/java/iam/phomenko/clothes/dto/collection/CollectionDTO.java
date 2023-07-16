package iam.phomenko.clothes.dto.collection;

import iam.phomenko.clothes.domain.clothes.Clothes;
import iam.phomenko.clothes.domain.clothes.Collection;
import iam.phomenko.clothes.domain.users.User;
import iam.phomenko.clothes.dto.clothes.ClothesDTO;
import iam.phomenko.clothes.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionDTO {
    private String id;
    private String name;
    private UserDTO creator;
    private List<ClothesDTO> clothesList;

    public CollectionDTO(Collection collection) {
        this.id= collection.getId();
        this.name= collection.getName();
        this.creator= new UserDTO(collection.getCreator());
        this.clothesList = collection.getClothesList().stream().parallel().map(ClothesDTO::new).collect(Collectors.toList());
    }
}
