package org.framework.adib.selendroidTestApp.StepDefinition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.framework.adib.core.baseclass.BaseClass;
import org.framework.adib.core.commonfunctions.CommonFunction;
import org.framework.adib.core.commonfunctions.CommonFunctionAndroidImpl;
import org.framework.adib.core.utilities.Log;
import org.framework.adib.core.utilities.XlsReader;
import org.framework.adib.selendroidTestApp.config.Config;
import org.framework.adib.selendroidTestApp.pages.ConfirmRegistrationPage;
import org.framework.adib.selendroidTestApp.pages.HomePage;
import org.framework.adib.selendroidTestApp.pages.NewRegistrationPage;
import org.openqa.selenium.Alert;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class SelendroidTest extends BaseClass {

    String name, userName, email, pwd, lang, adds;
    int rowNum = 0;
    public static XlsReader XLR = null;
    Scenario scenario=null;
    CommonFunction com_fun = null;
    @SuppressWarnings("rawtypes")
    public static AppiumDriver driver;

    protected String appUrl;

    protected HomePage hp;
    protected NewRegistrationPage rp;
    protected ConfirmRegistrationPage cp = null;

    Properties CONFIG;

    @SuppressWarnings("unchecked")
    @Before
    public void beforeMethod(Scenario scenario) throws Exception {
        
        DOMConfigurator.configure("log4j.xml");
        
        //getting instance of scenario
        this.scenario = scenario;
        Log.startTestCase(scenario.getName());
        
        // Initialize Configurations
        CONFIG= Config.initializeConfig();

        // Initialize App location
        appUrl = (System.getProperty("user.dir") + CONFIG.getProperty("APP_URL"));
        Log.info("App location:"+ appUrl);

        // Initialize Appium Driver
        BaseClass.openApplication(appUrl, CONFIG);
        driver = BaseClass.getDriver();

        // Initialize XCEL for Data
        XLR = new XlsReader(System.getProperty("user.dir") + CONFIG.getProperty("DATA_FILE"));

        // Initialize Common Function
        com_fun = new CommonFunctionAndroidImpl((AndroidDriver<AndroidElement>) driver);
    }

    @After
    public void afterMethod() {
        if (driver != null)
            driver.quit();
        // BaseClass.stopServer();
    }

    @SuppressWarnings("unchecked")
    @Given("^User opens Selendroid application$")
    public void user_opens_Selendroid_application() throws Throwable {
        
        hp = new HomePage((AndroidDriver<AndroidElement>) driver);
    }

    @When("^User clicks on Create New User icon$")
    public void user_clicks_on_Create_New_User_icon() throws Throwable {
        rp = hp.clickNewRegBttn();
    }

    @When("^Unser provides valid details and click on submit button$")
    public void unser_provides_valid_details_and_click_on_submit_button() throws Throwable {
        int rowCount = XLR.getRowCount("New_Registration");
        for (int i = 2; i <= rowCount; i++) {
            if (XLR.getCellData("New_Registration", "Is_Used", i).isEmpty()) {
                name = XLR.getCellData("New_Registration", "Name", i);
                userName = XLR.getCellData("New_Registration", "UserName", i);
                email = XLR.getCellData("New_Registration", "Email", i);
                pwd = XLR.getCellData("New_Registration", "Password", i);
                lang = XLR.getCellData("New_Registration", "Language", i);
                adds = XLR.getCellData("New_Registration", "Add_Required", i);
                rowNum = i;
                break;
            }
        }
        if (rowNum == 0) {
            throw new NullPointerException("Please provide a valid record in the data file!!!");
        }

        rp.clearTypeUserName(userName);

        rp.clearTypeEmailText(email);

        rp.clearTypePwdText(pwd);

        rp.clearTypeNameText(name);

        com_fun.hideKeyBoard();

        if (adds.equalsIgnoreCase("TRUE")) {
            if (rp.addsCkBoxValue() != true) {
                rp.selectCkBoxAdds();
            }
        }
        /*
         * driver.pressKeyCode(AndroidKeyCode.HOME); Thread.sleep(2000);
         * driver.pressKeyCode(187);
         * driver.findElementById("com.android.systemui:id/app_thumbnail_image")
         * .click() ; Thread.sleep(2000);
         */
        com_fun.hideKeyBoard();
        cp = rp.clickNewRegBttn();
    }

    @Then("^User details should be populated correctly on verify screen$")
    public void user_details_should_be_populated_correctly_on_verify_screen() throws Throwable {
        assertEquals(cp.getTextNameVerText(), name);
        assertEquals(cp.getTextUserNameVerText(), userName);
        assertEquals(cp.getTextEmailVerText(), email);
        assertEquals(cp.getTextPasswordVerText(), pwd);
        assertEquals(cp.getTextAcceptAddsVerText(), adds);
        assertEquals(cp.getTextLangVerText(), lang);
        XLR.setCellData("New_Registration", "Is_Used", rowNum, "Used");
    }

    @When("^user clicks on Confirm button$")
    public void user_clicks_on_Confirm_button() throws Throwable {
        hp = cp.clickFinalRegisterBttn();

    }

    @Then("^homepage should be displayed$")
    public void homepage_should_be_displayed() throws Throwable {
        assertTrue(hp.appTitleTextDisplayed());
    }

    @When("^User clicks on Display a Toast button$")
    public void user_clicks_on_Display_a_Toast_button() throws Throwable {
        hp.clickDisplayToast();
    }

    @Then("^Toast message should be displayed$")
    public void toast_message_should_be_displayed() throws Throwable {
        String ss = com_fun.readToastMsg();
        assertTrue(ss.contains("Hello selendroid toast!"));

    }

    @When("^User clicks on Display Popup button$")
    public void user_clicks_on_Display_Popup_button() throws Throwable {
        hp.clickDisplayPopup();
    }

    @Then("^Popup should be displayed and user able to dismiss it$")
    public void popup_should_be_displayed_and_user_able_to_dismiss_it() throws Throwable {
        Alert simplealert = driver.switchTo().alert();
        simplealert.dismiss();
    }
}
