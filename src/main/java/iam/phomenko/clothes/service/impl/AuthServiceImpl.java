package iam.phomenko.clothes.service.impl;

import iam.phomenko.clothes.config.jwt.JwtUtils;
import iam.phomenko.clothes.domain.users.User;
import iam.phomenko.clothes.dto.auth.LoginDTO;
import iam.phomenko.clothes.dto.user.UserDTO;
import iam.phomenko.clothes.exception.NotActivatedException;
import iam.phomenko.clothes.repository.UserRepository;
import iam.phomenko.clothes.service.AuthService;
import lombok.extern.java.Log;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Log
public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthServiceImpl(UserRepository repository, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Map.Entry<String, UserDTO> login(LoginDTO dto) throws NotActivatedException,DisabledException {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        User user = repository.getByUsername(userDetails.getUsername());
        if (!user.isActivated())
            throw new NotActivatedException("Your account need activation");
        if (!user.isEnabled())
            throw new DisabledException("Your account was disabled");
        return Map.entry(jwtCookie.toString(), new UserDTO(user));
    }

    @Override
    public String logout() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return cookie.toString();
    }
}
