package example;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AdvancedTest {

    @Test
    void assumptionExample() {
        Assumptions.assumeTrue(
            "test".equals(System.getProperty("env")),
            "Run with -Denv=test"
        );

        assertTrue(2 + 3 == 5);
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void windowsOnlyTest() {
        assertTrue(
            System.getProperty("os.name").toLowerCase().contains("windows")
        );
    }

    @TestFactory
    Stream<DynamicTest> dynamicNumberTests() {
        return Stream.of(1, 2, 3)
            .map(number ->
                DynamicTest.dynamicTest(
                    "Number " + number + " is positive",
                    () -> assertTrue(number > 0)
                )
            );
    }
}
