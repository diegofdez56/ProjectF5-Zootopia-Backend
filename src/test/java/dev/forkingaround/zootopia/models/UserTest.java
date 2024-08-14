package dev.forkingaround.zootopia.models;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserDefaultConstructor() {
        User user = new User();
        
        assertNull(user.getId());
        assertNull(user.getUsername());
        assertNull(user.getPassword());
        assertNull(user.getProfile());
        assertNull(user.getRoles());
    }

    @Test
    void testUserConstructorWithUsernameAndPassword() {
        User user = new User("john_doe", "password123");
        
        assertNull(user.getId());
        assertEquals("john_doe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertNull(user.getProfile());
        assertNull(user.getRoles());
    }

    @Test
    void testUserConstructorWithProfile() {
        Profile profile = new Profile();
        User user = new User("john_doe", "password123", profile);

        assertNull(user.getId());
        assertEquals("john_doe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals(profile, user.getProfile());
        assertNull(user.getRoles());
    }

    @Test
    void testSetters() {
        User user = new User();
        Profile profile = new Profile();
        Set<Role> roles = new HashSet<>();
        roles.add(new Role());

        user.setId(1L);
        user.setUsername("john_doe");
        user.setPassword("password123");
        user.setProfile(profile);
        user.setRoles(roles);

        assertEquals(1L, user.getId());
        assertEquals("john_doe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals(profile, user.getProfile());
        assertEquals(roles, user.getRoles());
    }

    @Test
    void testUserEqualsAndHashCode() {
        Profile profile = new Profile();
        User user1 = new User("john_doe", "password123", profile);
        User user2 = new User("john_doe", "password123", profile);
        
        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());

        User user3 = new User("different_user", "password123", profile);
        assertNotEquals(user1, user3);
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }
    
    @Test
    void testUserRoleSet() {
        User user = new User();
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        roles.add(role);
        user.setRoles(roles);
        
        assertNotNull(user.getRoles());
        assertTrue(user.getRoles().contains(role));
    }
    
    @Test
    void testUserProfile() {
        Profile profile = new Profile();
        User user = new User();
        user.setProfile(profile);
        
        assertEquals(profile, user.getProfile());
    }
    
    @Test
    void testUserWithRoles() {
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        roles.add(role);
        User user = new User();
        user.setRoles(roles);

        assertEquals(roles, user.getRoles());
        assertTrue(user.getRoles().contains(role));
    }
}
