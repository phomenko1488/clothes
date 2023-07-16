package iam.phomenko.clothes.dto.clothes;

import iam.phomenko.clothes.domain.clothes.Clothes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClothesDTO {
    private String id;
    private String name;
    private BigDecimal price;

    public ClothesDTO(Clothes clothes) {
        this.id = clothes.getId();
        this.name = clothes.getName();
        this.price = clothes.getPrice();
    }
}
