package iam.phomenko.clothes.repository;

import iam.phomenko.clothes.domain.clothes.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothesRepository extends JpaRepository<Clothes,Long> {
    Clothes getById(Long id);
}
