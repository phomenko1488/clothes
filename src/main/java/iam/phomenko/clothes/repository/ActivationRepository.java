package iam.phomenko.clothes.repository;

import iam.phomenko.clothes.domain.users.ActivationLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivationRepository extends JpaRepository<ActivationLink, String> {
    ActivationLink getActivationLinkById(String id);
}
