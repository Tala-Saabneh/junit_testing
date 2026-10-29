package example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calculator;

    @BeforeAll
    static void startAllTests() {
        System.out.println("=== Starting all tests ===");
    }

    @BeforeEach
    void setUp(TestInfo info) {
        calculator = new Calculator();
        System.out.println("Starting test: " + info.getDisplayName());
    }

    @AfterEach
    void cleanUp() {
        System.out.println("Test finished");
    }

    @AfterAll
    static void finishAllTests() {
        System.out.println("=== All tests finished ===");
    }

    @Test
    @DisplayName("Addition returns the correct result")
    @Tag("fast")
    void additionTest() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    void subtractionTest() {
        assertEquals(6, calculator.subtract(10, 4));
    }

    @Test
    void multiplicationTest() {
        assertEquals(12, calculator.multiply(3, 4));
    }

    @Test
    void assertionsExample() {
        assertNotEquals(10, calculator.add(2, 3));
        assertTrue(calculator.isPositive(5));
        assertFalse(calculator.isPositive(-2));
        assertNotNull(calculator.getMessage());

        assertAll(
            () -> assertEquals(5, calculator.add(2, 3)),
            () -> assertEquals(6, calculator.subtract(10, 4)),
            () -> assertEquals(12, calculator.multiply(3, 4))
        );
    }

    @Test
    void exceptionTest() {
        assertThrows(
            IllegalArgumentException.class,
            () -> calculator.divide(10, 0)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void doubleNumberMustBePositive(int number) {
        assertTrue(calculator.doubleNumber(number) > 0);
    }

    @ParameterizedTest
    @CsvSource({
        "2, 3, 5",
        "4, 6, 10",
        "10, 5, 15"
    })
    void additionWithDifferentValues(int first, int second, int expected) {
        assertEquals(expected, calculator.add(first, second));
    }

    @RepeatedTest(3)
    void repeatedAdditionTest(RepetitionInfo info) {
        assertEquals(5, calculator.add(2, 3));
        System.out.println(
            "Repetition: " + info.getCurrentRepetition()
            + " of " + info.getTotalRepetitions()
        );
    }

    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    void operationMustFinishInTime() {
        assertEquals(10, calculator.multiply(2, 5));
    }

    @Test
    void temporaryDirectoryTest(@TempDir Path directory) throws Exception {
        Path file = directory.resolve("message.txt");
        Files.writeString(file, calculator.getMessage());
        assertEquals("Hello JUnit", Files.readString(file));
    }

    @Test
    @Disabled("Demonstration: this test is intentionally skipped")
    void disabledTest() {
        assertEquals(100, calculator.add(2, 3));
    }

    @Test
    void testInfoExample(TestInfo info) {
        assertTrue(info.getDisplayName().contains("testInfoExample"));
    }

    @Nested
    @DisplayName("Division tests")
    class DivisionTests {

        @Test
        void divisionReturnsCorrectResult() {
            assertEquals(5, calculator.divide(10, 2));
        }

        @Test
        void divisionByZeroThrowsException() {
            assertThrows(
                IllegalArgumentException.class,
                () -> calculator.divide(10, 0)
            );
        }
    }
}
