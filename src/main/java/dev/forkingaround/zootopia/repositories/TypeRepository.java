package dev.forkingaround.zootopia.repositories;

import dev.forkingaround.zootopia.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    Type findByName(String name);
}

