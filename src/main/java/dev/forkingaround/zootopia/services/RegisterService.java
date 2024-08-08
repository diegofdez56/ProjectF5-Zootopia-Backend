package dev.forkingaround.zootopia.services;

import java.util.HashSet;
import java.util.Set;

import dev.forkingaround.zootopia.facades.EncoderFacade;
import dev.forkingaround.zootopia.dtos.UserDto;
import dev.forkingaround.zootopia.models.Role;
import dev.forkingaround.zootopia.models.User;
import dev.forkingaround.zootopia.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    UserRepository repository;
    RoleService roleService;
    EncoderFacade encoderFacade;

    public RegisterService(UserRepository repository, RoleService roleService, EncoderFacade encoderFacade) {
        this.repository = repository;
        this.roleService = roleService;
        this.encoderFacade = encoderFacade;
    }

    public String save(UserDto newUserDto) {

        String passwordDecoded = encoderFacade.decode("base64", newUserDto.getPassword());
        String passwordEncoded = encoderFacade.encode("bcrypt",  passwordDecoded);

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