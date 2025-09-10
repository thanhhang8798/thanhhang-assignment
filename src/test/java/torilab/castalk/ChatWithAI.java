package torilab.castalk;

import appium.ManageAppiumServer;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class ChatWithAI {
    private AndroidDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void setup() throws MalformedURLException {
        String appiumServerUrl = "http://127.0.0.1:4723";

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName", "Android");
        dc.setCapability("appium:automationName", "uiautomator2");
        dc.setCapability("appium:app", System.getProperty("user.dir") + "\\apps\\castalk_uat2.11.0.apk");

        ManageAppiumServer.startAppiumServer();
        driver = new AndroidDriver(new URL(appiumServerUrl), dc);
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        explicitWait.until(ExpectedConditions.elementToBeClickable(AppiumBy
                .androidUIAutomator("new UiSelector().text(\"Agree and Continue\")"))).click();

        explicitWait.until(ExpectedConditions.elementToBeClickable(AppiumBy
                .id("com.android.permissioncontroller:id/permission_deny_button"))).click();

        explicitWait.until(ExpectedConditions.elementToBeClickable(AppiumBy
                .androidUIAutomator("new UiSelector().text(\"OK\")"))).click();
    }

    @Test
    public void Chat_01_Open_Chat() {
        explicitWait.until(ExpectedConditions.elementToBeClickable(AppiumBy
                .androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(2)"))).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy
                .androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")")));
        Assert.assertTrue(driver.findElement(AppiumBy
                .androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")")).isDisplayed());
    }

    @Test
    public void Chat_02_End_Chat() {
        explicitWait.until(ExpectedConditions.elementToBeClickable(AppiumBy
                .androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(0)"))).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy
                .androidUIAutomator("new UiSelector().text(\"Home\")")));
        Assert.assertTrue(driver.findElement(AppiumBy
                .androidUIAutomator("new UiSelector().text(\"Home\")")).isDisplayed());
    }

    @AfterClass
    public void close() {
        driver.quit();
        ManageAppiumServer.stopAppiumServer();
    }
}
