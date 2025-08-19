# Carbohydrate Calculator - Selenium + Cucumber + Extent (Maven)

This project automates the first 3 test cases for https://www.calculator.net/carbohydrate-calculator.html

## Tech
- Java 11
- Selenium 4
- Cucumber JVM 7 (Gherkin)
- JUnit 4 runner
- Extent Reports (Spark) via `extentreports-cucumber7-adapter`
- WebDriverManager (auto driver binaries)
- Maven

## Structure
```
src
 ├─ test
 │   ├─ java
 │   │   ├─ com.example.framework    # DriverFactory, Hooks, PropertyLoader
 │   │   ├─ com.example.app.pages    # Page Objects
 │   │   └─ com.example.app.steps    # Step Definitions
 │   │
 │   └─ resources
 │       ├─ features                 # .feature files
 │       ├─ config.properties        # baseUrl, browser, waits
 │       ├─ extent.properties        # Extent adapter settings
 │       └─ extent-config.xml        # Extent look-and-feel
 └─ pom.xml
```

## Scenarios
1. **Load calculator page successfully**
2. **Calculate with US units and default activity**
3. **Blank Age field throws validation error**

## Run
```bash
mvn clean verfy
```
Reports:
- Cucumber HTML: `target/cucumber-html/index.html`
- Extent (Spark): `target/extent/spark.html`

## Config
- `config.properties`:
  - `baseUrl=https://www.calculator.net/carbohydrate-calculator.html`
  - `browser=firefox` (chrome|firefox|edge)
  - `implicitWaitSec=10`

