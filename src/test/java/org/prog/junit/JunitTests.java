package org.prog.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JunitTests extends ParentTest {

    @BeforeEach
    public void beforeTest() {
        System.out.println("~~~~~~~~~~~~~~~~~~");
    }

    @Test
    public void test1() {
        System.out.println("Hello JUnit 1!");
    }

    @Test
    public void test2() {
        System.out.println("Hello JUnit 2!");
    }
}
