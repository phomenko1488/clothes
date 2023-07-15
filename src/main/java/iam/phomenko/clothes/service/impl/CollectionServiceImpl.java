package iam.phomenko.clothes.service.impl;

import iam.phomenko.clothes.domain.clothes.Collection;
import iam.phomenko.clothes.domain.users.User;
import iam.phomenko.clothes.repository.CollectionRepository;
import iam.phomenko.clothes.service.CollectionService;
import iam.phomenko.clothes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialExpiredException;

@Service
public class CollectionServiceImpl implements CollectionService {
    private final CollectionRepository repository;
    private final UserService userService;

    @Autowired
    public CollectionServiceImpl(CollectionRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public Collection getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public Collection create(String name, Authentication authentication) throws CredentialExpiredException {
        User creator = userService.getUserByAuthentication(authentication);
        if (creator == null)
            throw new CredentialExpiredException("");
        Collection collection = new Collection();
        collection.setCreator(creator);
        collection.setName(name.trim());
        return repository.save(collection);
    }
}
