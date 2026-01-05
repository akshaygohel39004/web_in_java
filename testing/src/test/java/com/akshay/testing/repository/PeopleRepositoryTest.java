package com.akshay.testing.repository;

import com.akshay.testing.entity.People;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
//@Rollback(value = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PeopleRepositoryTest {

    @Autowired
    PeopleRepository repository;

    @Test
    void shouldSavePeople() {
        People p = new People();
        p.setName("akshay");
        People saved = repository.save(p);

        assertNotNull(saved.getId());
    }
}
