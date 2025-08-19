package com.example.app.steps;

import com.example.app.pages.CarbohydrateCalculatorPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Map;

public class CarbohydrateSteps {
    private final CarbohydrateCalculatorPage page = new CarbohydrateCalculatorPage();

    @Given("I launch the carbohydrate calculator")
    public void i_launch_the_calculator() {
        page.open();
        Assert.assertTrue("Calculator page did not load.", page.isMainFormVisible());
    }

    @Then("the calculator page should load with the main form visible")
    public void page_should_load() {
        Assert.assertTrue(
                "Page title didn’t match",
                page.isPageLoaded()
        );
        Assert.assertTrue(
                "Calculator form not visible",
                page.isMainFormVisible()
        );
    }


    @When("I select the US unit system")
    public void select_us_unit_system() {
        page.selectUSSystem();
    }

    @When("I enter the following values:")
    public void enter_values(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> row = dataTable.asMaps().get(0);
        page.setAge(row.get("AgeYears"));
        page.setSex(row.get("Sex"));
        page.setHeightUS(row.get("HeightFeet"), row.get("HeightInches"));
        page.setWeightLbs(row.get("WeightLbs"));
    }

    @When("I click Calculate")
    public void click_calculate() {
        page.clickCalculate();
    }

    @Then("I should see a carbohydrate result")
    public void should_see_result() {
        Assert.assertTrue("No carbohydrate result visible.", page.isCInfoTableVisible());
    }

    @Then("I should see validation message")
    public void ageValidationMessage() {
        Assert.assertTrue("No carbohydrate result visible.", page.isAgeValidationMessageVisible());
    }
}
