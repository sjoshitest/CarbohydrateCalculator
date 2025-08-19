package com.example.app.pages;

import com.example.framework.DriverFactory;
import com.example.framework.PropertyLoader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

public class CarbohydrateCalculatorPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Properties props = PropertyLoader.load();

    private final By mainForm = By.cssSelector("form[action*='carbohydrate-calculator']");
    private final By systemUS = By.xpath("//a[normalize-space(.)=\"US Units\"]\n");
    private final By age = By.id("cage");
    private final By sexMaleLabel = By.cssSelector("label[for='csex1']");
    private final By sexFemaleLabel = By.cssSelector("label[for='csex2']");
    private final By heightFeet = By.xpath("//input[@id='cheightfeet']\n");
    private final By heightInches = By.xpath("//input[@id='cheightinch']\n");
    private final By weightLbs = By.xpath("//input[@id='cpound']\n");
    private final By calculateBtn = By.xpath("//input[@type='submit' or @value='Calculate' or @id='calbutton'] | //button[contains(.,'Calculate')]");
    private final By resultBlock = By.cssSelector("table.cinfoT");
    private final By ageValidationMsg = By.xpath(
            "//font[@color='red' and normalize-space() = 'Please provide an age between 18 and 80.']"
    );

    public CarbohydrateCalculatorPage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isCarbohydrateCalculatorPageTitleCorrect() {
        String expectedTitle = props.getProperty("carbohydrateCalculatorPageTitle", "Carbohydrate Calculator");
        return driver.getTitle().trim().equals(expectedTitle);
    }

    public boolean isPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.titleContains("Carbohydrate Calculator"));
    }

    public void open() {
        String baseUrl = props.getProperty("baseUrl").trim();
        driver.get(baseUrl);
    }

    public boolean isMainFormVisible() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("topmenu")))
                .isDisplayed();
    }

    public boolean isMainFormPresent() {
        try {
            return driver.findElement(mainForm).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void selectUSSystem() {
        WebElement us = wait.until(
                ExpectedConditions.elementToBeClickable(systemUS)
        );
        us.click();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(age)
        );
    }

    public void setAge(String value) {
        driver.findElement(age).clear();
        driver.findElement(age).sendKeys(value);
    }

    public void setSex(String sex) {
        By labelLocator = sex.equalsIgnoreCase("male")
                ? sexMaleLabel
                : sexFemaleLabel;
        WebElement label = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(labelLocator));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", label
        );
        label.click();
    }


    public void setHeightUS(String feet, String inches) {
        driver.findElement(heightFeet).clear();
        driver.findElement(heightFeet).sendKeys(feet);
        driver.findElement(heightInches).clear();
        driver.findElement(heightInches).sendKeys(inches);
    }

    public void setWeightLbs(String lbs) {
        driver.findElement(weightLbs).clear();
        driver.findElement(weightLbs).sendKeys(lbs);
    }

    public boolean isCInfoTableVisible() {
        WebElement table = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(resultBlock));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", table
        );
        return table.isDisplayed();
    }

    public boolean isAgeValidationMessageVisible() {
        try {
            WebElement msg = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(ageValidationMsg)
            );
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});", msg
            );
            return "Please provide an age between 18 and 80.".equals(msg.getText().trim());
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickCalculate() {
        driver.findElement(calculateBtn).click();
    }

}
