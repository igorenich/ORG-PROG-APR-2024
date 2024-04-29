package org.prog.junit;

import org.junit.jupiter.api.Test;

public class JunitTests2 extends ParentTest {

    @Test
    public void test1() {
        System.out.println("Hello JUnit 1!");
    }

    @Test
    public void test2() {
        System.out.println("Hello JUnit 2!");
    }
}
