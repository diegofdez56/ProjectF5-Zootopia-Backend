package dev.forkingaround.zootopia.repositories;

import java.util.Optional;

import dev.forkingaround.zootopia.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUsername(String username);

}