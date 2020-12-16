package com.qa.stepdef;

import com.qa.pages.LoginPage;
import com.qa.pages.ProductDetailsPage;
import com.qa.pages.ProductsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class ProductStepDef {

    @Given("I am logged in using username {string} and password {string}")
    public void iAmLoggedIn(String username, String password) {
        new LoginPage().login(username,password);
    }

    @Then("The product is listed with title {string} and price {string}")
    public void theProductIsListedWithTitleAndPrice(String title, String price) {
        Assert.assertEquals(new ProductsPage().getSLBTitle(),title);
        Assert.assertEquals(new ProductsPage().getSLBPrice(),price);
    }

    @When("I click on product title")
    public void iClickOnProductTitle() {
        new ProductsPage().pressSLBTitle();
    }

    @Then("I should be on product details page with title {string}, price {string} and description {string}")
    public void iShouldBeOnProductDetailsPageWithTitlePriceAndDescription(String title, String price, String description) {

        Assert.assertEquals(new ProductDetailsPage().getSLBTitle(),title);
        Assert.assertEquals(new ProductDetailsPage().scrollToSLBPriceAndGetSLBPrice(),price);
        Assert.assertEquals(new ProductDetailsPage().getSLBTxt(),description);

    }
}
