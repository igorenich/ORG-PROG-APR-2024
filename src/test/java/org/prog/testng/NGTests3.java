package org.prog.testng;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class NGTests3 extends ParentNG {

    @Test
    public void someTestName5(){
        System.out.println("Hello TestsNG 5");
    }

    @Test
    public void someTestName6(){
        System.out.println("Hello TestsNG 6");
    }
}
