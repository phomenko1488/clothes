package iam.phomenko.clothes.controller;


import iam.phomenko.clothes.domain.users.User;
import iam.phomenko.clothes.dto.auth.LoginDTO;
import iam.phomenko.clothes.dto.auth.SignUpDTO;
import iam.phomenko.clothes.dto.pojo.ErrorDTO;
import iam.phomenko.clothes.dto.pojo.SuccessDTO;
import iam.phomenko.clothes.dto.user.EmailExistDTO;
import iam.phomenko.clothes.dto.user.UserDTO;
import iam.phomenko.clothes.dto.user.UsernameExistDTO;
import iam.phomenko.clothes.exception.EmailExistException;
import iam.phomenko.clothes.exception.NotActivatedException;
import iam.phomenko.clothes.exception.UsernameExistException;
import iam.phomenko.clothes.service.AuthService;
import iam.phomenko.clothes.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
//@CrossOrigin(allowedHeaders = "*", allowCredentials = "true", origins = "*", value = "*")
@Log
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@RequestBody LoginDTO dto) {
        try {
            Map.Entry<String, UserDTO> login = authService.login(dto);
            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, login.getKey())
                    .body(login.getValue());
        } catch (NotActivatedException | DisabledException e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody SignUpDTO dto) {
        try {
            User user = userService.create(dto);
            return ResponseEntity.ok().body(new SuccessDTO(user.getId()));
        } catch (EmailExistException | UsernameExistException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, authService.logout())
                .body(new SuccessDTO("You've been signed out!"));
    }

    @PostMapping("/existByUsername")
    public ResponseEntity<Object> usernameExists(@RequestBody UsernameExistDTO dto) {
        boolean result = userService.existsByUsername(dto);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/existByEmail")
    public ResponseEntity<Object> emailExists(@RequestBody EmailExistDTO dto) {
        boolean result = userService.existsByEmail(dto);
        return ResponseEntity.ok().body(result);
    }

}
