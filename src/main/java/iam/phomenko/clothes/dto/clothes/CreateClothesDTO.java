package iam.phomenko.clothes.dto.clothes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClothesDTO {
    private String name;
    private String collectionId;
    private BigDecimal price;
    private List<String> photos;
}
