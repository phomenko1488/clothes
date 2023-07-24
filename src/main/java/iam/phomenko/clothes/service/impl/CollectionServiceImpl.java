package iam.phomenko.clothes.service.impl;

import iam.phomenko.clothes.domain.clothes.Collection;
import iam.phomenko.clothes.domain.users.User;
import iam.phomenko.clothes.dto.collection.CollectionCreateDTO;
import iam.phomenko.clothes.exception.DomainNotFoundException;
import iam.phomenko.clothes.repository.CollectionRepository;
import iam.phomenko.clothes.service.CollectionService;
import iam.phomenko.clothes.service.UserService;
import iam.phomenko.clothes.utils.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.security.auth.login.CredentialExpiredException;

@Service
public class CollectionServiceImpl implements CollectionService {
    private final CollectionRepository repository;
    private final UserService userService;
    private final Generator generator;

    @Autowired
    public CollectionServiceImpl(CollectionRepository repository, UserService userService, Generator generator) {
        this.repository = repository;
        this.userService = userService;
        this.generator = generator;
    }

    @Override
    public Collection getById(String id) throws DomainNotFoundException {
        Collection collection = repository.getCollectionById(id);
        if (collection == null)
            throw new DomainNotFoundException("Collection with such id doesn't exists");
        return collection;
    }

    @Override
    public Collection create(CollectionCreateDTO dto, Authentication authentication) throws CredentialExpiredException {
        User creator = userService.getUserByAuthentication(authentication);
        if (creator == null)
            throw new CredentialExpiredException("You was logged out");
        Collection collection = new Collection();
        collection.setId(generator.generateId());
        collection.setCreator(creator);
        collection.setName(dto.getName().trim());
        collection = repository.save(collection);
        creator.getCollections().add(collection);
        userService.save(creator);
        return collection;
    }

    @Override
    public Collection save(Collection collection) {
        return repository.save(collection);
    }
}
