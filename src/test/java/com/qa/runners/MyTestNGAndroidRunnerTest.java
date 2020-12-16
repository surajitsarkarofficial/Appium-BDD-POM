package com.qa.runners;

import com.qa.utils.DriverManager;
import com.qa.utils.GlobalParams;
import com.qa.utils.ServerManager;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.apache.logging.log4j.ThreadContext;
import org.testng.annotations.*;

@CucumberOptions(
        plugin = {"pretty"
                , "html:target/ExecutionReports/Android/cucumber"
                , "de.monochromata.cucumber.report.PrettyReports:target/ExecutionReports/Android/cucumber-html-reports"}
        ,features = {"src/test/resources"}
        ,glue ={"com.qa.stepdef"}
        , monochrome = true
        //, tags = "@Login"
)
public class MyTestNGAndroidRunnerTest extends RunnerBase{


}
