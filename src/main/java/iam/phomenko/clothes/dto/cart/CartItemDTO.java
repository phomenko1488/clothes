package iam.phomenko.clothes.dto.cart;

import iam.phomenko.clothes.domain.cart.CartItem;
import iam.phomenko.clothes.domain.clothes.ClothesSize;
import iam.phomenko.clothes.dto.clothes.ClothesDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {
    private Long count;
    private ClothesDTO clothes;
    private ClothesSize size;

    public CartItemDTO(CartItem item) {
        this.count = item.getCount();
        this.size = item.getSize();
        this.clothes = new ClothesDTO(item.getClothes());
    }
}
