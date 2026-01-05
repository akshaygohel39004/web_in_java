package com.akshay.testing;

import com.akshay.testing.entity.People;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class TestingApplicationTests {

    @DisplayName("t1")
    @Test
    void basicTesting(){
        //here all testmethod are written in one method for learning purpose,it should be written based on they requirements.
        assertEquals(10,10,"both is not same");
        assertNotNull(new Object(),"objecct shoud not be null");
        assertAll(
                () -> assertNotNull(new Object()),
                () -> assertEquals("Akshay","Akshay"),
                () -> assertTrue(true)
        );
    }




    @DisplayName("t2")
    @Tag("f1")
    @Test
    @Disabled
    void objectIdentity(){
        People p1=new People();
        p1.setName("Akshay");
        People p2=new People();
        p2.setName("Akshay");

        assertSame(p1,p2);
    }

    @DisplayName("t3")
    @Tag("f1")
    @Test
    void objecteqality(){
        People p1=new People();
        p1.setName("Akshay");
        People p2=new People();
        p2.setName("Akshay");

        assertEquals(p1,p2);
    }

    @DisplayName("t4")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3,-5,20})
    void testPositiveNumbers(int number) {
        assertTrue(number > 0);
    }

    @DisplayName("t5")
    @ParameterizedTest
    @CsvSource({
            "2, 3, 5",
            "3, 4, 7",
            "4, 5, 9"
    })
    void testAdd(int a, int b, int expected) {
        assertEquals(expected, a+b);
    }

}
