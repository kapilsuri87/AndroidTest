package org.framework.adib.selendroidTestApp.StepDefinition;

import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.framework.adib.core.baseclass.BaseClass;
import org.framework.adib.core.commonfunctions.CommonFunction;
import org.framework.adib.core.commonfunctions.CommonFunctionAndroidImpl;
import org.framework.adib.core.utilities.Log;
import org.framework.adib.core.utilities.XlsReader;
import org.framework.adib.selendroidTestApp.config.Config;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Setup {
  
    public static AndroidDriver<AndroidElement> driver;
    
    public static XlsReader XLR = null;
    Scenario scenario = null;
    public static Properties CONFIG;
    static CommonFunction com_fun = null;
    protected static String appUrl;
    
    @Before
    public void beforeMethod(Scenario scenario) throws Exception {
        
        DOMConfigurator.configure("log4j.xml");
        
        // getting instance of scenario
        this.scenario = scenario;
        Log.startTestCase(scenario.getName());
        
        // Initialize Configurations
        CONFIG = Config.initializeConfig();
        
        // Initialize App location
        appUrl = (System.getProperty("user.dir") + CONFIG.getProperty("APP_URL"));
        Log.info("App location:" + appUrl);
       
        // Initialize Appium Driver
        BaseClass.openApplication(appUrl, CONFIG);
        driver =BaseClass.getAndroidDriver();
        
        // Initialize XCEL for Data
        XLR = new XlsReader(System.getProperty("user.dir") + CONFIG.getProperty("DATA_FILE"));
        
        // Initialize Common Function
        com_fun = new CommonFunctionAndroidImpl(driver);
    }
    
    @After
    public void afterMethod(Scenario scenario) {
        if (scenario.isFailed())
        {
            Log.info("Test Case failed");
        }
        if (driver != null) {
            driver.quit();
        }
       if (BaseClass.checkIfServerIsRunnning(Integer.parseInt(CONFIG.getProperty("APPIUM_PORT")))) {
            BaseClass.stopServer();
       }
    }
    
}
