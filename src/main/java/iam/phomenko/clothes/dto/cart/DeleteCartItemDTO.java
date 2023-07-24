package iam.phomenko.clothes.dto.cart;

import iam.phomenko.clothes.domain.clothes.ClothesSize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCartItemDTO {
    private String itemId;
    private ClothesSize size;
}
