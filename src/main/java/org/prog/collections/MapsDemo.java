package org.prog.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapsDemo {


    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        System.out.println(map.get("key1"));
        System.out.println(map.get("key2"));

        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            System.out.println(map.get(key));
        }
    }
}
