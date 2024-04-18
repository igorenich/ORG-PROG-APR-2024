package org.prog.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListDemo {

    //індексований список
    //[0], [1], [2], [3]..[189]..[99999999999999999999]
    // 1. 189old -> 190
    // 2. 190old -> 191
    List<String> arrayList = new ArrayList<>();

    //двохзв'язний список
    //[1st element] - [elementA] - [elementB] - [elementC] -.....- [last element]
    // I want to insert [elementBC] between B and C
    // 1. set elementB next neighbour to BC
    // 2. set elementC previous neighbour to BC
    // 3. set element CB next neighbour C
    // 4. set element CB previous  neighbour B
    List<String> linkedLints = new LinkedList<>();

    public static void main(String[] args) {
        List<String> sList = new ArrayList<>();
        sList.add("string1");
        sList.add("string2");
        sList.add("string3");
        sList.add("string4");
        sList.add("string5");
        sList.add("string5");
        sList.add("string5");
        sList.add("string5");
        sList.add("string5");
        sList.add("string5");
        sList.add("string5");

        sList.remove(0);
        sList.remove("string4");

//        for (int i = 0; i < sList.size(); i++) {
//            System.out.println(sList.get(i));
//        }

        for (String s : sList) {
            System.out.println(s);
        }
    }
}
