package com.qa.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.io.IOException;

public class DriverManager {

    public static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    TestUtils utils = new TestUtils();

    public AppiumDriver getDriver()
    {
        return driver.get();
    }

    public void setDriver(AppiumDriver driver)
    {
        DriverManager.driver.set(driver);
    }

    public void initializeDriver() throws Exception {
        AppiumDriver localDriver =null;
        GlobalParams params = new GlobalParams();
        PropertyManager propertyManager = new PropertyManager();

        try{
            utils.log().info("Initializing appium driver...");
            switch (params.getPlatformName())
            {
                case "Android" :
                    localDriver = new AndroidDriver(new ServerManager().getServer().getUrl(),new CapabilitiesManager().getCapabilities());
                    break;
                case "iOS" :
                    localDriver = new IOSDriver(new ServerManager().getServer().getUrl(),new CapabilitiesManager().getCapabilities());
                    break;
            }
            if(localDriver==null)
            {
                throw new Exception("driver is null...ABOT!!!");
            }
            utils.log().info("Driver is initialized...");
            driver.set(localDriver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            utils.log().fatal("Driver initialization failed.. ABORT !!! "+e.toString());
            throw e;
        }

    }
    /**
     * This method will close the app and quit the driver session
     */
    public void closeApp() {
        ((InteractsWithApps) getDriver()).closeApp();
    }

    /**
     * This method will launch the app and re-start the driver session
     */
    public void launchApp() {
        ((InteractsWithApps) getDriver()).launchApp();
    }

}
