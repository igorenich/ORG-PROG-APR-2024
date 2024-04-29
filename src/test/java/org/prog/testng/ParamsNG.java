package org.prog.testng;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParamsNG {

    @Test(dataProvider = "provider1")
    public void paramTest(String s, int i) {
        if (i == 20) {
            Assert.fail("This test has failed!");
        }
        Assert.assertEquals(i, 10, "expected i to be 10!");
        System.out.println(s + i);
    }

    @DataProvider(name = "provider1")
    public Object[][] dataProvider() {
        return new Object[][]{
                {"i'm printing ", 10},
                {"i'm printing ", 20},
                {"i'm printing ", 15},
                {"i'm printing ", 11}
        };
    }
}
