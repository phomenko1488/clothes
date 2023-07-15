package iam.phomenko.clothes.service;

import iam.phomenko.clothes.domain.users.User;
import iam.phomenko.clothes.exception.EmailExistException;
import iam.phomenko.clothes.exception.UsernameExistException;
import org.springframework.security.core.Authentication;

public interface UserService {
    User create(String username, String password, String email) throws EmailExistException, UsernameExistException;

    User getById(Long id);

    User getUserByAuthentication(Authentication authentication);
}
