package iam.phomenko.clothes.repository;

import iam.phomenko.clothes.domain.clothes.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<Collection, String > {
}
