package iam.phomenko.clothes.controller;

import iam.phomenko.clothes.domain.users.User;
import iam.phomenko.clothes.dto.pojo.ErrorDTO;
import iam.phomenko.clothes.dto.user.UserDTO;
import iam.phomenko.clothes.exception.DomainNotFoundException;
import iam.phomenko.clothes.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok().body(new UserDTO(userService.getById(id)));
        } catch (Exception | DomainNotFoundException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }
}
