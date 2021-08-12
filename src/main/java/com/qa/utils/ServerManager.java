package com.qa.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.util.HashMap;

public class ServerManager {
    public static ThreadLocal<AppiumDriverLocalService> server = new ThreadLocal<>();
    TestUtils utils = new TestUtils();

    public AppiumDriverLocalService getServer()
    {
        return server.get();
    }

    public void startServer() throws Exception {
        AppiumDriverLocalService localServer=null;
        try {
            utils.log().info("Starting appium server...");
            String os = System.getProperty("os.name");
            if(os.contains("Windows"))
            {
                utils.log().info("Starting appium server on Windows.");
                localServer=getWindowsAppiumServer();
            }
            else if(os.contains("Mac")){
                utils.log().info("Starting appium server on MAC OS.");
                localServer = getMACAppiumServer();
            }else{
                utils.log().fatal("Unsupported OS. ABORT!!!");
                throw new Exception("Unsupported OS. ABORT!!!");
            }
            localServer.start();
            if (localServer == null || !localServer.isRunning()) {
                utils.log().fatal("Appium server not started.. ABORT!!!");
                throw new AppiumServerHasNotBeenStartedLocallyException("Appium server not started.. ABORT!!!");
            }
            //localServer.clearOutPutStreams();
            server.set(localServer);
            utils.log().info("Appium server started...");
        }catch(Exception e)
        {
            if(localServer!=null)
            {
                localServer.stop();
            }
            throw e;
        }
    }


    /**
     * return the default server
     * @return AppiumDriverLocalService
     */
    public AppiumDriverLocalService getAppiumServerDefault() {
        return AppiumDriverLocalService.buildDefaultService();
    }

    /**
     * This method will start the appium server
     * @return AppiumDriverLocalService
     */
    public AppiumDriverLocalService getWindowsAppiumServer()
    {
        GlobalParams params = new GlobalParams();
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
        .usingAnyFreePort()
        .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
        .withLogFile(new File("target" + File.separator+"logs"+File.separator+ params.getPlatformName()+"_"
        + params.getDeviceName()+ File.separator+"Server.log")));
    }

    /**
     * Thsi method will return the Appium server for MAC machine
     * @return AppiumDriverLocalService
     */
    public AppiumDriverLocalService getMACAppiumServer() {
        GlobalParams params = new GlobalParams();
        HashMap<String, String> environment = new HashMap<String, String>();
        environment.put("PATH", "/usr/local/bin:/Library/Java/JavaVirtualMachines/jdk-15.0.1.jdk/Contents/Home/bin=/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:/Library/Apple/usr/bin:/Users/suro/apache-maven-3.8.1/bin:/Users/suro/Library/Android/sdk/tools:/Users/suro/Library/Android/sdk/platform-tools:/Users/suro/Library/Android/sdk/build-tools" + System.getenv("PATH"));
        environment.put("ANDROID_HOME", "/Users/suro/Library/Android/sdk");
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File("/usr/local/bin/node"))
                .withAppiumJS(new File("/Applications/Appium.app/Contents/Resources/app/node_modules/appium/build/lib/main.js"))
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withEnvironment(environment)
                .withLogFile(new File("target" + File.separator+"logs"+File.separator+ params.getPlatformName()+"_"
                        + params.getDeviceName()+ File.separator+"Server.log")));
    }
}
