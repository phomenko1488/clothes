package iam.phomenko.clothes.repository;

import iam.phomenko.clothes.domain.users.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getRoleById(Long id);
}
