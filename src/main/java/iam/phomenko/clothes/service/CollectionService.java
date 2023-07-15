package iam.phomenko.clothes.service;

import iam.phomenko.clothes.domain.clothes.Collection;
import org.springframework.security.core.Authentication;

import javax.security.auth.login.CredentialExpiredException;

public interface CollectionService {
    Collection getById(Long id);

    Collection create(String name, Authentication authentication) throws CredentialExpiredException;

}
