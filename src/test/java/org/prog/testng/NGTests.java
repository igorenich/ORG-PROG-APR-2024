package org.prog.testng;

import org.testng.annotations.*;

public class NGTests extends ParentNG {


    @Test
    public void someTestName1(){
        System.out.println("Hello TestsNG 1");
    }

    @Test
    public void someTestName2(){
        System.out.println("Hello TestsNG 2");
    }
}
