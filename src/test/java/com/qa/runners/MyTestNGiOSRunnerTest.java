package com.qa.runners;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty"
                , "html:target/ExecutionReports/iOS/cucumber"
                , "de.monochromata.cucumber.report.PrettyReports:target/ExecutionReports/iOS/cucumber-html-reports"}
        ,features = {"src/test/resources"}
        ,glue ={"com.qa.stepdef"}
        , monochrome = true
        //, tags = "@Login"
)
public class MyTestNGiOSRunnerTest extends RunnerBase{


}
