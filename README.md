# JUnit Practice — JUnit 6.1.3

A small project demonstrating JUnit Jupiter features in Java.

## Requirements
- Java 17 or newer
- Maven 3.6.3 or newer (Maven 3.9.x recommended)
- Internet access for the first dependency download

## Open in VS Code
1. Extract the ZIP.
2. Open the `junit-practice` folder in VS Code.
3. Wait for Maven dependencies to load.
4. Run tests from the Testing panel, or use the terminal.

## Run all tests
```powershell
mvn clean test
```

## Run one test method
```powershell
mvn test "-Dtest=CalculatorTest#additionTest"
```

## Run tests tagged `fast`
```powershell
mvn test "-Dgroups=fast"
```

## Run with the `env=test` system property
```powershell
mvn test "-Denv=test"
```

## Included topics
- Lifecycle annotations: `@BeforeAll`, `@BeforeEach`, `@AfterEach`, `@AfterAll`
- Assertions: `assertEquals`, `assertTrue`, `assertFalse`, `assertThrows`, `assertAll`
- `TestInfo` and assumptions
- Parameterized tests: `@ValueSource`, `@CsvSource`
- Repeated and dynamic tests
- `@DisplayName`, `@Tag`, `@Disabled`, `@Nested`
- `@Timeout`, `@TempDir`
- Platform output capture and configuration properties

One test is intentionally disabled to demonstrate `@Disabled`.
