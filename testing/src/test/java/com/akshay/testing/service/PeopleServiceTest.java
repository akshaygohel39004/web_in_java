package com.akshay.testing.service;

import com.akshay.testing.entity.People;
import com.akshay.testing.repository.PeopleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class PeopleServiceTest {


    @Mock
    PeopleRepository repository;

    @InjectMocks
    PeopleService service;

    @Test
    void shouldSavePeople() {
        People p = new People();
        p.setName("Akshay");

        when(repository.save(p)).thenReturn(p);

        People result = service.addPeople(p);

        assertNotNull(result);
        assertEquals("Akshay", result.getName());
        verify(repository).save(p);
    }

    @Test
    void shouldThrowException_whenNameIsNull() {
        People p = new People();

        assertThrows(IllegalArgumentException.class,
                () -> service.addPeople(p));
    }

    @Test
    void privateTesting(){
        ReflectionTestUtils.invokeMethod(service,"privateMethodCall");
    }
}
