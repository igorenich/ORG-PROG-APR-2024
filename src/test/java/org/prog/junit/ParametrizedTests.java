package org.prog.junit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ParametrizedTests {


    @ParameterizedTest
    @MethodSource("testSources")
    public void test(String s, String s2) {
        System.out.println(s + " - " + s2);
    }

    public static Stream<Arguments> testSources() {
        return Stream.of(
                Arguments.of("part one", "part two", "part three"),
                Arguments.of("string", "2"),
                Arguments.of("string", "3"),
                Arguments.of("string", "4"),
                Arguments.of("string", "5"),
                Arguments.of("string", "6"),
                Arguments.of("string", "7"),
                Arguments.of("string", "8")
        );
    }
}
