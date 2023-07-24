package iam.phomenko.clothes.domain.cart;

import iam.phomenko.clothes.domain.clothes.Clothes;
import iam.phomenko.clothes.domain.clothes.ClothesSize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Clothes clothes;
    private Long count;
    private ClothesSize size;
}
