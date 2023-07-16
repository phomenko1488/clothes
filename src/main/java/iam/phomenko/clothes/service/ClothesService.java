package iam.phomenko.clothes.service;

import iam.phomenko.clothes.domain.clothes.Clothes;
import iam.phomenko.clothes.dto.clothes.CreateClothesDTO;
import iam.phomenko.clothes.exception.CollectionDontExistException;
import org.springframework.security.core.Authentication;

import javax.persistence.EntityNotFoundException;

public interface ClothesService {
    Clothes create(CreateClothesDTO dto, Authentication authentication) throws CollectionDontExistException;

    Clothes getById(String id) throws EntityNotFoundException;

}
