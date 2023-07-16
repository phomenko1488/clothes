package iam.phomenko.clothes.service.impl;

import iam.phomenko.clothes.consts.Mails;
import iam.phomenko.clothes.domain.users.ActivationLink;
import iam.phomenko.clothes.domain.users.Role;
import iam.phomenko.clothes.domain.users.User;
import iam.phomenko.clothes.dto.auth.SignUpDTO;
import iam.phomenko.clothes.dto.user.EmailExistDTO;
import iam.phomenko.clothes.dto.user.UsernameExistDTO;
import iam.phomenko.clothes.exception.EmailExistException;
import iam.phomenko.clothes.exception.UsernameExistException;
import iam.phomenko.clothes.repository.ActivationRepository;
import iam.phomenko.clothes.repository.UserRepository;
import iam.phomenko.clothes.service.MailService;
import iam.phomenko.clothes.service.UserService;
import iam.phomenko.clothes.utils.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final MailService mailService;
    private final Mails mails;
    private final ActivationRepository activationRepository;
    private final Generator generator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, MailService mailService, Mails mails, ActivationRepository activationRepository, Generator generator) {
        this.userRepository = userRepository;
        this.mailService = mailService;
        this.mails = mails;
        this.activationRepository = activationRepository;
        this.generator = generator;
    }

    @Override
    public User create(SignUpDTO dto) throws EmailExistException, UsernameExistException {
        if (userRepository.existsByUsername(dto.getUsername()))
            throw new UsernameExistException("Username already exists");
        if (userRepository.existsByEmail(dto.getEmail()))
            throw new EmailExistException("Email already exists");
        User user = new User();
        user.setId(generator.generateId());
        user.setEnabled(true);
        user.setActivated(false);
        user.setBalance(BigDecimal.ZERO);
        user.setRole(new Role(1L, "ROLE_USER"));
        user.setEmail(dto.getEmail().trim());
        user.setUsername(dto.getUsername().trim());
        user.setPassword(passwordEncoder.encode(dto.getPassword().trim()));
        user = userRepository.save(user);
        ActivationLink link = activationRepository.save(new ActivationLink(UUID.randomUUID().toString(), user));
        String activation = "Activation";
        mailService.sendMail(dto.getEmail(), activation, String.format(mails.getMailTemplateByName(activation), user.getUsername(), link.getId()));
        return user;
    }

    @Override
    public User getById(String id) {
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

    @Override
    public boolean existsByEmail(EmailExistDTO dto) {
        return userRepository.existsByEmail(dto.getEmail());
    }

    @Override
    public boolean existsByUsername(UsernameExistDTO dto) {
        return userRepository.existsByUsername(dto.getUsername());
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
