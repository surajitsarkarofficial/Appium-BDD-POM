package com.qa.stepdef;

import com.qa.utils.DriverManager;
import com.qa.utils.GlobalParams;
import com.qa.utils.ServerManager;
import com.qa.utils.VideoManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;

public class Hooks {

    @Before
    public void initialize() throws Exception {
        new DriverManager().closeApp();
        new DriverManager().launchApp();
        //new VideoManager().startRecording();
    }

    @After
    public void quit(Scenario scenario) throws IOException {


        if(scenario.isFailed())
        {
           byte[] screenshot= new DriverManager().getDriver().getScreenshotAs(OutputType.BYTES);
           scenario.attach(screenshot,"img/png",scenario.getName());
        }
        //new VideoManager().stopRecording(scenario.getName());


    }
}
