package com.example.framework;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Hooks {

    @Before
    public void setUp() {
        DriverFactory.initDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverFactory.getDriver();
        if (scenario.isFailed() && driver != null) {
            try {
                byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(bytes, "image/png", "Failure Screenshot");
                String ssDir = System.getProperty("screenshot.dir", "target/screenshots/");
                Files.createDirectories(Paths.get(ssDir));
                File file = new File(ssDir + scenario.getName().replaceAll("[^a-zA-Z0-9._-]", "_") + ".png");
                FileUtils.writeByteArrayToFile(file, bytes);
            } catch (IOException e) {
                // ignore
            }
        }
        DriverFactory.quitDriver();
    }
}
