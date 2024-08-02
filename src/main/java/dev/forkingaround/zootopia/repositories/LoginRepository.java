package dev.forkingaround.zootopia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.forkingaround.zootopia.models.User;

public interface LoginRepository extends JpaRepository<User, Long> {
}