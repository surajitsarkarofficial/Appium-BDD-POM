package com.qa.utils;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class CapabilitiesManager {
    TestUtils utils = new TestUtils();

    public DesiredCapabilities getCapabilities() throws IOException {
        GlobalParams params = new GlobalParams();
        Properties props = new PropertyManager().getProperties();

        try{
            utils.log().info("Getting capabilities...");
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, params.getPlatformName());
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, params.getDeviceName());
            desiredCapabilities.setCapability(MobileCapabilityType.UDID, params.getUdid());

            switch(params.getPlatformName()) {
                case "Android":
                    desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
                    desiredCapabilities.setCapability("appPackage", props.getProperty("androidAppPackage"));
                    desiredCapabilities.setCapability("appActivity", props.getProperty("androidAppActivity"));
                    desiredCapabilities.setCapability("systemPort", params.getSystemPort());
                    desiredCapabilities.setCapability("chromeDriverPort", params.getChromeDriverPort());
                    String androidAppUrl =System.getProperty("user.dir")+ File.separator+"src"+File.separator+"test"
                            +File.separator + "resources"+File.separator+"apps"+File.separator+
                            "Android.SauceLabs.Mobile.Sample.app.2.7.1.apk";
                    desiredCapabilities.setCapability("app", androidAppUrl);
                    break;
                case "iOS":
                    desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                    String iOSAppPath = System.getProperty("user.dir")+ File.separator+"src"+File.separator+"test"
                            +File.separator + "resources"+File.separator+"apps"+File.separator+
                            "iOS.Simulator.SauceLabs.Mobile.Sample.app.2.7.1.app";
                    desiredCapabilities.setCapability("bundleId", props.getProperty("iOSBundleId"));
                    desiredCapabilities.setCapability("wdaLocalPort", params.getWdaLocalPort());
                    desiredCapabilities.setCapability("webkitDebugProxyPort", params.getWebkitDebugProxyPort());
                    desiredCapabilities.setCapability("launchTimeout",180000);
                    desiredCapabilities.setCapability("wdaConnectionTimeout",600000);
                    desiredCapabilities.setCapability("app", iOSAppPath);
            }
            return desiredCapabilities;
        }catch(Exception e)
        {
            e.printStackTrace();
            utils.log().fatal("Failed to load capabilities. ABORT!!! "+ e.toString());
            throw e;
        }

    }
}
