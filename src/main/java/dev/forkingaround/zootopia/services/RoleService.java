package dev.forkingaround.zootopia.services;

import dev.forkingaround.zootopia.exceptions.RoleNotFoundException;
import dev.forkingaround.zootopia.models.Role;
import dev.forkingaround.zootopia.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    
    RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role getById(Long id) {
        Role role = repository.findById(id).orElseThrow(() -> new RoleNotFoundException("Role not found"));
        return role;
    }

}