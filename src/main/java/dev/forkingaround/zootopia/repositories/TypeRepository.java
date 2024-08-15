package dev.forkingaround.zootopia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import dev.forkingaround.zootopia.models.Type;

public interface TypeRepository extends JpaRepository<Type, Long> {
    Optional<Type> findByName(String name);
}

