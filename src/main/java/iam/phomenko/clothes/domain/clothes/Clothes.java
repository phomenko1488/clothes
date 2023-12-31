package iam.phomenko.clothes.domain.clothes;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "clothes")
public class Clothes {
    @Id
    private String id;


    private String name;

    private BigDecimal price;

    @ManyToOne
    private Collection collection;

    @ElementCollection
    private List<String> photos;

}
