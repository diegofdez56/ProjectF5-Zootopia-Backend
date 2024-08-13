package dev.forkingaround.zootopia.models;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserTest {

    @Test
    void testUserConstructorAndGetters() {
        Profile profile = new Profile();
        User user = new User("john_doe", "password123", profile);

        assertEquals("john_doe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertNotNull(user.getProfile());
        assertEquals(profile, user.getProfile());
    }

    @Test
    void testUserSetters() {
        User user = new User();
        Profile profile = new Profile();
        Set<Role> roles = new HashSet<>();
        
        user.setUsername("john_doe");
        user.setPassword("password123");
        user.setProfile(profile);
        user.setRoles(roles);

        assertEquals("john_doe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals(profile, user.getProfile());
        assertEquals(roles, user.getRoles());
    }

    @Test
    void testUserDefaultConstructor() {
        User user = new User();

        assertEquals(null, user.getUsername());
        assertEquals(null, user.getPassword());
        assertEquals(null, user.getProfile());
        assertEquals(null, user.getRoles());
    }
}
