package dev.forkingaround.zootopia.services;

import java.util.HashSet;
import java.util.Set;

import dev.forkingaround.zootopia.dtos.UserDto;
import dev.forkingaround.zootopia.models.Role;
import dev.forkingaround.zootopia.models.User;
import dev.forkingaround.zootopia.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    UserRepository repository;
    RoleService roleService;

    public RegisterService(UserRepository repository, RoleService roleService) {
        this.repository = repository;
        this.roleService = roleService;
    }

    public String save(UserDto newUserDto) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordEncoded = encoder.encode(newUserDto.getPassword());

        User user = new User(newUserDto.getUsername(), passwordEncoded);
        user.setRoles(assignDefaultRole());

        repository.save(user);

        return "Success";
    }

    public Set<Role> assignDefaultRole() {
        Role defaultRole = roleService.getById(1L);

        Set<Role> roles = new HashSet<>();
        roles.add(defaultRole);

        return roles;
    }

}
