package iam.phomenko.clothes.service;


import iam.phomenko.clothes.dto.auth.LoginDTO;
import iam.phomenko.clothes.dto.user.UserDTO;
import iam.phomenko.clothes.exception.NotActivatedException;
import org.springframework.security.authentication.DisabledException;

import java.util.Map;

public interface AuthService {

    Map.Entry<String, UserDTO> login(LoginDTO dto) throws NotActivatedException, DisabledException;

    String logout();

}
