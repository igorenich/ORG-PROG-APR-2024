package org.prog.collections;

import java.util.HashSet;
import java.util.Set;

public class SetDemo {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("string1");
        set.add("string2");
        set.add("string3");
        set.add("string3");
        set.add("string3");
        set.add("string3");

        for (String s : set){
            System.out.println(s);
        }
    }
}
