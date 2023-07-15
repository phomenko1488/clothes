package iam.phomenko.clothes.service.impl;

import iam.phomenko.clothes.domain.users.Role;
import iam.phomenko.clothes.repository.RoleRepository;
import iam.phomenko.clothes.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role getById(Long id) {
        return repository.getRoleById(id);
    }
}
