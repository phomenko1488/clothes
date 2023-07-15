package iam.phomenko.clothes.service.impl;

import iam.phomenko.clothes.domain.users.Role;
import iam.phomenko.clothes.domain.users.User;
import iam.phomenko.clothes.exception.EmailExistException;
import iam.phomenko.clothes.exception.UsernameExistException;
import iam.phomenko.clothes.repository.UserRepository;
import iam.phomenko.clothes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(String username, String password, String email) throws EmailExistException, UsernameExistException {
        if (userRepository.existsByUsername(username))
            throw new UsernameExistException();
        if (userRepository.existsByEmail(email))
            throw new EmailExistException();
        User user = new User();
        user.setEnabled(true);
        user.setBalance(BigDecimal.ZERO);
        user.setRole(new Role(1L, "ROLE_USER"));
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getByUsername(username);
    }

    @Override
    public User getUserByAuthentication(Authentication authentication) {
        if (authentication == null)
            return null;
        return userRepository.getByUsername(((User) authentication.getPrincipal()).getUsername());
    }
}
