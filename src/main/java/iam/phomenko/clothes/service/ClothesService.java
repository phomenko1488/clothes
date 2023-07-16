package iam.phomenko.clothes.service;

import iam.phomenko.clothes.domain.clothes.Clothes;
import iam.phomenko.clothes.exception.CollectionDontExistException;
import org.springframework.security.core.Authentication;

import java.math.BigDecimal;
import java.util.List;

public interface ClothesService {
    Clothes create(String name, String collectionId, List<String> photos, BigDecimal price, Authentication authentication) throws CollectionDontExistException;

    Clothes getById(String id);
}
