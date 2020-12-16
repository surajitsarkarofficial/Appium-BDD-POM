package com.qa.pages;


import com.qa.pages.MenuPage;
import com.qa.utils.TestUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ProductDetailsPage extends MenuPage {
	TestUtils utils = new TestUtils();
	
	@AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]")
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeOther[@name=\"test-Description\"]/child::XCUIElementTypeStaticText[1]")
	private MobileElement SLBTitle;
	
	@AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[2]"
			+ "") 
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeOther[@name=\"test-Description\"]/child::XCUIElementTypeStaticText[2]")
	private MobileElement SLBTxt;
	
	@AndroidFindBy (accessibility = "test-Price")
	@iOSXCUITFindBy(accessibility = "test-Price")
	private MobileElement SLBPrice;
	
	@AndroidFindBy (accessibility = "test-BACK TO PRODUCTS") 
	@iOSXCUITFindBy (id = "test-BACK TO PRODUCTS")
	private MobileElement backToProductsBtn;

	@AndroidFindBy(accessibility = "test-ADD TO CART")
	@iOSXCUITFindBy (id = "test-ADD TO CART")
	public MobileElement addToCartBtn;

public String getSLBTitle() {
	String title = getText(SLBTitle, "title is - ");
	return title;
}

public String getSLBTxt() {
	String txt = getText(SLBTxt, "txt is - ");
	return txt;
}

	/*
	 * public String getSLBPrice() { String price = getText(SLBPrice);
	 * utils.log("price is - " + price); return price; }
	 */

public String scrollToSLBPriceAndGetSLBPrice() {
	scrollToElement(SLBPrice);
	return getText(SLBPrice, "");
}


public Boolean isAddToCartBtnDisplayed() {
	return isElementDisplayed(addToCartBtn);
}

public ProductsPage pressBackToProductsBtn() {
	click(backToProductsBtn, "navigate back to products page");
	return new ProductsPage();
}

}
