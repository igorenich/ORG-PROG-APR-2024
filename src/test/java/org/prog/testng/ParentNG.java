package org.prog.testng;

import org.testng.annotations.*;

public class ParentNG {

    @BeforeSuite
    public void setUp() {
        System.out.println("----- START OF TESTS ------");
    }

    @AfterSuite
    public void tearDown() {
        System.out.println("----- END OF TESTS ------");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("======== START OF A TEST ========");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("======== END OF A TEST ========");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("............................");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("............................");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.print(">");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("<");
    }

}
