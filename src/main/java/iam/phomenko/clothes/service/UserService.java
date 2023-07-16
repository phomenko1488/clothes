package iam.phomenko.clothes.service;

import iam.phomenko.clothes.domain.users.User;
import iam.phomenko.clothes.dto.auth.SignUpDTO;
import iam.phomenko.clothes.dto.user.EmailExistDTO;
import iam.phomenko.clothes.dto.user.UsernameExistDTO;
import iam.phomenko.clothes.exception.EmailExistException;
import iam.phomenko.clothes.exception.UsernameExistException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.Authentication;

import javax.persistence.EntityNotFoundException;

public interface UserService {
    User create(SignUpDTO dto) throws EmailExistException, UsernameExistException;

    User getById(String id) throws EntityNotFoundException;

    User getUserByAuthentication(Authentication authentication);

    boolean existsByEmail(EmailExistDTO dto);
    boolean existsByUsername(UsernameExistDTO dto);

    User save(User user);
}
