package org.framework.adib.selendroidTestApp.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    static Properties CONFIG = new Properties();
    
    /** 
     * Method to initialize configuration.
     * 
     */
    public static Properties initializeConfig() throws IOException {
        FileInputStream fis=null;
        try {
            fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/config.properties");
            CONFIG.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(fis!=null)
                fis.close();
        }
        return CONFIG;
    }

}
