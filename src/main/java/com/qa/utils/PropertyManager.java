package com.qa.utils;



import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class  PropertyManager {
    private static Properties props = new Properties();
    TestUtils utils = new TestUtils();

    public Properties getProperties() throws IOException {
        InputStream is=null;
        String propFileName = "config.properties";

        if(props.isEmpty()){
            try{
                utils.log().info("Loading config properties...");
                is = getClass().getClassLoader().getResourceAsStream(propFileName);
                props.load(is);
            } catch (IOException e) {
                e.printStackTrace();
                utils.log().fatal("Failed to load properties.. ABORT!!! "+e.toString());
                throw e;
            }finally {
                if(is!=null)
                {
                    is.close();
                }
            }
        }
        return props;
    }
}
