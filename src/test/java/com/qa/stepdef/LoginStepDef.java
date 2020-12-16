package com.qa.stepdef;

import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginStepDef {

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        new LoginPage().waitForVisibility(new LoginPage().loginBtn);
    }

    @When("I enter the username {string}")
    public void iEnterTheUsername(String userName) {
        new LoginPage().enterUserName(userName);
    }

    @When("I enter the password {string}")
    public void iEnterThePassword(String password) {
        new LoginPage().enterPassword(password);
    }

    @When("I login")
    public void iLogin() {
        new LoginPage().pressLoginBtn();
    }

    @Then("Login should fail with an error {string}")
    public void loginShouldFailWithAnError(String errMsg) {
        Assert.assertEquals(new LoginPage().getErrTxt(),errMsg);
    }

    @Then("I should see Products page with title {string}")
    public void iShouldSeeProductsPageWithTitle(String title) {
        Assert.assertEquals(new ProductsPage().getTitle(),title);
    }

}
