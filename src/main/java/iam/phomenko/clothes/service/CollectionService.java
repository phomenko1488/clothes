package iam.phomenko.clothes.service;

import iam.phomenko.clothes.domain.clothes.Collection;
import iam.phomenko.clothes.dto.collection.CollectionCreateDTO;
import org.springframework.security.core.Authentication;

import javax.security.auth.login.CredentialExpiredException;

public interface CollectionService {
    Collection getById(String id);

    Collection create(CollectionCreateDTO dto, Authentication authentication) throws CredentialExpiredException;

}
