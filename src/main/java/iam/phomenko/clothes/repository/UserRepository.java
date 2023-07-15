package iam.phomenko.clothes.repository;

import iam.phomenko.clothes.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getById(Long id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    User getByUsername(String username);
}
