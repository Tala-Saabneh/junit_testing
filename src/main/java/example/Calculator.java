package example;

public class Calculator {

    public int add(int first, int second) {
        return first + second;
    }

    public int subtract(int first, int second) {
        return first - second;
    }

    public int multiply(int first, int second) {
        return first * second;
    }

    public int divide(int first, int second) {
        if (second == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return first / second;
    }

    public int doubleNumber(int number) {
        return number * 2;
    }

    public boolean isPositive(int number) {
        return number > 0;
    }

    public String getMessage() {
        return "Hello JUnit";
    }
}
