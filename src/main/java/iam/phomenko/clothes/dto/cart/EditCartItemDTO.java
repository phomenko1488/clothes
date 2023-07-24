package iam.phomenko.clothes.dto.cart;

import iam.phomenko.clothes.domain.clothes.ClothesSize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditCartItemDTO {
    private Boolean isSizeEdited;
    private ClothesSize size;
    private Long amount;
    private String itemId;
}
