package org.prog.junit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class ParentTest {

    @BeforeAll
    public static void setUp(){
        System.out.println("=========START OF TESTS============");
    }

    @AfterAll
    public static void tearDown(){
        System.out.println("=========END OF TESTS============");
    }

    @BeforeEach
    public void beforeTest() {
        System.out.println("--------------------");
    }

    @AfterEach
    public void afterTest() {
        System.out.println("--------------------");
    }
}
