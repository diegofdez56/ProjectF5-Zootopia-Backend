package dev.forkingaround.zootopia.services;

import dev.forkingaround.zootopia.exceptions.RoleNotFoundException;
import dev.forkingaround.zootopia.models.Role;
import dev.forkingaround.zootopia.repositories.RoleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    RoleServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetByIdRoleFound() {
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");

        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));

        Role foundRole = roleService.getById(1L);

        assertNotNull(foundRole);
        assertEquals(1L, foundRole.getId());
        assertEquals("ROLE_USER", foundRole.getName());
    }

    @Test
    void testGetByIdRoleNotFound() {
        when(roleRepository.findById(anyLong())).thenReturn(Optional.empty());

        RoleNotFoundException thrown = assertThrows(
            RoleNotFoundException.class,
            () -> roleService.getById(1L)
        );

        assertEquals("Role not found", thrown.getMessage());
    }
}
