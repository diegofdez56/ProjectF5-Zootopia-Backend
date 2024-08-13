package dev.forkingaround.zootopia.models;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SecurityUserTest {

    @Test
    void testGetPassword() {
        User user = new User();
        user.setPassword("password123");
        SecurityUser securityUser = new SecurityUser(user);
        assertEquals("password123", securityUser.getPassword());
    }

    @Test
    void testGetUsername() {
        User user = new User();
        user.setUsername("john_doe");
        SecurityUser securityUser = new SecurityUser(user);
        assertEquals("john_doe", securityUser.getUsername());
    }

    @Test
    void testGetAuthorities() {
        User user = new User();
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setName("ROLE_USER");
        roles.add(role);
        user.setRoles(roles);
        SecurityUser securityUser = new SecurityUser(user);

        Collection<? extends GrantedAuthority> authorities = securityUser.getAuthorities();
        assertNotNull(authorities);
        assertEquals(1, authorities.size());
        GrantedAuthority authority = authorities.iterator().next();
        assertEquals("ROLE_USER", authority.getAuthority());
    }

    @Test
    void testIsAccountNonExpired() {
        User user = new User();
        SecurityUser securityUser = new SecurityUser(user);
        assertTrue(securityUser.isAccountNonExpired());
    }

    @Test
    void testIsAccountNonLocked() {
        User user = new User();
        SecurityUser securityUser = new SecurityUser(user);
        assertTrue(securityUser.isAccountNonLocked());
    }

    @Test
    void testIsCredentialsNonExpired() {
        User user = new User();
        SecurityUser securityUser = new SecurityUser(user);
        assertTrue(securityUser.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        User user = new User();
        SecurityUser securityUser = new SecurityUser(user);
        assertTrue(securityUser.isEnabled());
    }
}
