package dev.forkingaround.zootopia.repositories;

import dev.forkingaround.zootopia.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
