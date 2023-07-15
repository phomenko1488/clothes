package iam.phomenko.clothes.domain.clothes;

import iam.phomenko.clothes.domain.users.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "collections")
public class Collection {

    @Id
    private Long id;

    private String name;
    @ManyToOne
    private User creator;
    @OneToMany
    private List<Clothes> clothesList;

}
