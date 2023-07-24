package iam.phomenko.clothes.service.impl;

import iam.phomenko.clothes.domain.clothes.Clothes;
import iam.phomenko.clothes.domain.clothes.Collection;
import iam.phomenko.clothes.domain.users.User;
import iam.phomenko.clothes.dto.clothes.CreateClothesDTO;
import iam.phomenko.clothes.exception.CollectionDontExistException;
import iam.phomenko.clothes.exception.DomainNotFoundException;
import iam.phomenko.clothes.repository.ClothesRepository;
import iam.phomenko.clothes.service.ClothesService;
import iam.phomenko.clothes.service.CollectionService;
import iam.phomenko.clothes.service.UserService;
import iam.phomenko.clothes.utils.Generator;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Log
public class ClothesServiceImpl implements ClothesService {
    private final ClothesRepository clothesRepository;
    private final UserService userService;
    private final CollectionService collectionService;
    private final Generator generator;

    @Autowired
    public ClothesServiceImpl(ClothesRepository clothesRepository, UserService userService, CollectionService collectionService, Generator generator) {
        this.clothesRepository = clothesRepository;
        this.userService = userService;
        this.collectionService = collectionService;
        this.generator = generator;
    }

    @Override
    public Clothes create(CreateClothesDTO dto, Authentication authentication) throws CollectionDontExistException {
        User creator = userService.getUserByAuthentication(authentication);
        if (creator == null)
            throw new CredentialsExpiredException("You are logged out");
        Collection collection;
        try {
            collection = collectionService.getById(dto.getCollectionId());
        } catch (DomainNotFoundException e) {
            throw new CollectionDontExistException("Collection dont exists");
        }
        if (!creator.equals(collection.getCreator()))
            throw new AccessDeniedException("It's not your collection");
        Clothes clothes = new Clothes();
        clothes.setId(generator.generateId());
        clothes.setName(dto.getName().trim());
        clothes.setPhotos(dto.getPhotos());
        clothes.setCollection(collection);
        clothes.setPrice(dto.getPrice());
        clothes = clothesRepository.save(clothes);
        collection.getClothesList().add(clothes);
        collectionService.save(collection);
        return clothes;
    }

    @Override
    public Clothes getById(String id) throws DomainNotFoundException {
        Clothes clothes = clothesRepository.getClothesById(id);
        if (clothes == null)
            throw new DomainNotFoundException("Clothes with such id doesn't exists");
        return clothes;
    }
}
