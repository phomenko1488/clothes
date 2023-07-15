package iam.phomenko.clothes.domain.clothes;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "clothes")
public class Clothes {
    @Id
    private Long id;

    @ManyToOne
    private Collection collection;

    private String name;

    private BigDecimal price;


}
