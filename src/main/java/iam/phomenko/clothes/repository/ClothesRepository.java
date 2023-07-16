package iam.phomenko.clothes.repository;

import iam.phomenko.clothes.domain.clothes.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;

public interface ClothesRepository extends JpaRepository<Clothes,String > {
    Clothes getById(String id);
}
