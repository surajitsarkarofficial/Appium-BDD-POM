package com.qa.pages;

import com.qa.utils.DriverManager;
import com.qa.utils.GlobalParams;
import com.qa.utils.TestUtils;
import io.appium.java_client.*;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;

public class BasePage {
    private AppiumDriver<?> driver;
    TestUtils utils = new TestUtils();

    public BasePage()
    {
        this.driver = new DriverManager().getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(this.driver),this);
    }

    public void waitForVisibility(MobileElement e) {
        WebDriverWait wait = new WebDriverWait(this.driver, TestUtils.WAIT);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void waitForVisibility(By e){
        WebDriverWait wait = new WebDriverWait(this.driver, TestUtils.WAIT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(e));
    }

    /**
     * This method will clear the text in the field
     * @param e
     */
    public void clear(MobileElement e) {
        waitForVisibility(e);
        e.clear();
    }

    /**
     * This method will click on the element
     * @param e
     */
    private void click(MobileElement e) {
        waitForVisibility(e);
        e.click();
    }

    /**
     * This method will call the click method and log the specified message
     * @param e
     * @param msg
     */
    public void click(MobileElement e, String msg) {
        System.out.println(msg);
        click(e);
    }

    /**
     * This method will enter the text in the element
     * @param e
     * @param txt
     */
    private void sendKeys(MobileElement e, String txt) {
        waitForVisibility(e);
        e.sendKeys(txt);
    }

    /**
     * This method will call the entertext method to enter the text and log the message
     * @param e
     * @param txt
     * @param msg
     */
    public void sendKeys(MobileElement e, String txt, String msg) {
        System.out.println(msg);
        sendKeys(e,txt);
    }

    /**
     * This method will fetch the attribute value from the element.
     * @param e
     * @param attribute
     * @return String
     */
    public String getAttribute(MobileElement e, String attribute) {
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }

    /**
     * This method will fetch the text of the element and return the text
     * @param e
     * @return Stirng
     */
    private String getText(MobileElement e) {
        String txt = null;
        switch (new GlobalParams().getPlatformName()) {
            case "Android":
                txt = getAttribute(e, "text");
                break;
            case "iOS":
                txt = getAttribute(e, "label");
                break;
        }
        return txt;
    }
    /**
     * This method will fetch the text of the element and log the message
     * @param e
     * @param msg
     * @return Stirng
     */
    public String getText(MobileElement e, String msg) {

        System.out.println(msg);
        return getText(e);
    }

    /**
     * This method will verify if element is displayed
     * @param ele
     * @return Boolean
     */
    public boolean isElementDisplayed(MobileElement ele)
    {
        try{
            return ele.isDisplayed();
        }catch (Exception e)
        {
            return false;
        }
    }

    /**
     * This method will close the app and quit the driver session
     */
    public void closeApp() {
        ((InteractsWithApps) driver).closeApp();
    }

    /**
     * This method will launch the app and re-start the driver session
     */
    public void launchApp() {
        ((InteractsWithApps) driver).launchApp();
    }



    /**
     * This method will scroll based on direction provided
     * @param direction
     */
    public void scroll(String direction)
    {
        Dimension dim = driver.manage().window().getSize();
        int x = dim.width/2;
        int startY = 0;
        int endY = 0;

        switch (direction)
        {
            case "up" : startY = (int) (dim.getHeight()*0.2);
                endY = (int) (dim.getHeight()*0.4);
                break;
            case "down" :startY = (int) (dim.getHeight()*0.4);
                endY = (int) (dim.getHeight()*0.2);
                break;
        }
        TouchAction ta = new TouchAction(driver);
        ta.press(PointOption.point(x,startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(x,endY)).release().perform();
    }

    /**
     * This method will scroll until the specified element is vissible in the screen
     * @param e
     */
    public void scrollToElement(MobileElement e)
    {
        int count=0;
        while(!isElementDisplayed(e) && count<10)
        {
            scroll("down");
            count ++;
        }
    }

}
