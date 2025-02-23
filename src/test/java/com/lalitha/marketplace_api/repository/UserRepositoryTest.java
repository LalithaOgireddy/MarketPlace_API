package com.lalitha.marketplace_api.repository;

import com.lalitha.marketplace_api.domain.entity.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager em;

    @Test
    void testexistsByEmail_existinguser_true() {
        User user =  userRepository.save(new User("test@lexicon.se","Password1!"));
        assertTrue(userRepository.existsByEmail("test@lexicon.se"));
    }

    @Test
    void testexistsByEmail_nonexistinguser_false() {
        User user =  userRepository.save(new User("test@lexicon.se","Password1!"));
        assertFalse(userRepository.existsByEmail("test1@lexicon.se"));
    }

    @Test
    void updateExpiredByEmail_existinguser_true() {
        User user =  userRepository.save(new User("test@lexicon.se","Password1!"));
        userRepository.save(user);
        userRepository.updateExpiredByEmail("test@lexicon.se",true);
        em.flush();
        em.clear();
        User updated = userRepository.getUserByEmail("test@lexicon.se");
        assertTrue(updated.isExpired());

    }

    @Test
    void updatePasswordByEmail() {
        User user =  userRepository.save(new User("test@lexicon.se","Password1!"));
        userRepository.save(user);
        userRepository.updatePasswordByEmail("test@lexicon.se","Password2!");
        em.flush();
        em.clear();
        User updated = userRepository.getUserByEmail("test@lexicon.se");
        assertEquals("Password2!", updated.getPassword());
    }

    @Test
    void getUserByEmail() {
        User user = userRepository.save(new User("test@lexicon.se","Password1!"));
        userRepository.save(user);
        em.flush();
        em.clear();
        User updated = userRepository.getUserByEmail("test@lexicon.se");
        assertEquals(user, updated);
    }
}