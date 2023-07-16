package iam.phomenko.clothes.service.impl;

import iam.phomenko.clothes.domain.clothes.Clothes;
import iam.phomenko.clothes.domain.clothes.Collection;
import iam.phomenko.clothes.domain.users.User;
import iam.phomenko.clothes.exception.CollectionDontExistException;
import iam.phomenko.clothes.repository.ClothesRepository;
import iam.phomenko.clothes.service.ClothesService;
import iam.phomenko.clothes.service.CollectionService;
import iam.phomenko.clothes.service.UserService;
import iam.phomenko.clothes.utils.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
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
    public Clothes create(String name, String collectionId, List<String> photos, BigDecimal price, Authentication authentication) throws CollectionDontExistException {
        User creator = userService.getUserByAuthentication(authentication);
        if (creator == null)
            throw new CredentialsExpiredException("You are logged out");
        Collection collection = collectionService.getById(collectionId);
        if (collection == null)
            throw new CollectionDontExistException();
        if (!creator.equals(collection.getCreator()))
            throw new AccessDeniedException("It's not your collection");
        Clothes clothes = new Clothes();
        clothes.setId(generator.generateId());
        clothes.setName(name.trim());
        clothes.setCollection(collection);
        clothes.setPrice(price);
        return clothes;
    }

    @Override
    public Clothes getById(String  id) {
        return clothesRepository.getById(id);
    }
}
