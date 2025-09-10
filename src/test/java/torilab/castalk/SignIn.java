package torilab.castalk;

import appium.ManageAppiumServer;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

public class SignIn {
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

        explicitWait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.android.permissioncontroller:id/permission_deny_button"))).click();


        explicitWait.until(ExpectedConditions.elementToBeClickable(AppiumBy
                .androidUIAutomator("new UiSelector().text(\"OK\")"))).click();

        explicitWait.until(ExpectedConditions.elementToBeClickable(AppiumBy
                .androidUIAutomator("new UiSelector().text(\"More\")"))).click();

        explicitWait.until(ExpectedConditions.elementToBeClickable(AppiumBy
                .androidUIAutomator("new UiSelector().text(\"Sign In\")"))).click();

        explicitWait.until(ExpectedConditions.elementToBeClickable(AppiumBy
                .androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(0)"))).click();
    }


    @Test
    public void SignIn_01_Login_Email_WrongFormat() {
        By emailTextbox = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailTextbox));
        driver.findElement(emailTextbox).sendKeys("castalk");

        By passwordTextbox = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(passwordTextbox));
        driver.findElement(passwordTextbox).sendKeys("Luu9i20nm@nj");

        explicitWait.until(ExpectedConditions.elementToBeClickable(AppiumBy
                .androidUIAutomator("new UiSelector().text(\"Sign In\")"))).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy
                .androidUIAutomator("new UiSelector().text(\"Incorrect email address format. Please try again.\")")));
        Assert.assertEquals(driver.findElement(AppiumBy
                        .androidUIAutomator("new UiSelector().text(\"Incorrect email address format. Please try again.\")"))
                .getText(), "Incorrect email address format. Please try again.");
    }

    @Test
    public void SignIn_02_Login_Incorrect_Email() {
        By emailTextbox = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailTextbox));
        driver.findElement(emailTextbox).clear();
        driver.findElement(emailTextbox).sendKeys("castalk@gmail.com");

        By passwordTextbox = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(passwordTextbox));
        driver.findElement(passwordTextbox).clear();
        driver.findElement(passwordTextbox).sendKeys("Luu9i20nm@nj");

        explicitWait.until(ExpectedConditions.elementToBeClickable(AppiumBy
                .androidUIAutomator("new UiSelector().text(\"Sign In\")"))).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy
                .androidUIAutomator("new UiSelector().text(\"Incorrect email address or password. Please try again.\")")));
        Assert.assertEquals(driver.findElement(AppiumBy
                        .androidUIAutomator("new UiSelector().text(\"Incorrect email address or password. Please try again.\")"))
                .getText(), "Incorrect email address or password. Please try again.");
    }

    @Test
    public void SignIn_03_Login_Incorrect_Password() {
        By emailTextbox = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailTextbox));
        driver.findElement(emailTextbox).clear();
        driver.findElement(emailTextbox).sendKeys("castalk6@gmail.com");

        By passwordTextbox = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(passwordTextbox));
        driver.findElement(passwordTextbox).clear();
        driver.findElement(passwordTextbox).sendKeys("Luu9");

        explicitWait.until(ExpectedConditions.elementToBeClickable(AppiumBy
                .androidUIAutomator("new UiSelector().text(\"Sign In\")"))).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy
                .androidUIAutomator("new UiSelector().text(\"Incorrect email address or password. Please try again.\")")));
        Assert.assertEquals(driver.findElement(AppiumBy
                        .androidUIAutomator("new UiSelector().text(\"Incorrect email address or password. Please try again.\")"))
                .getText(), "Incorrect email address or password. Please try again.");
    }

    @Test
    public void SignIn_04_Login_Empty_Email_Password() {
        By emailTextbox = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailTextbox));
        driver.findElement(emailTextbox).clear();

        By passwordTextbox = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(passwordTextbox));
        driver.findElement(passwordTextbox).clear();

        WebElement signInButton = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Sign In\")"));

        boolean isButtonDisabled = false;

        if (!signInButton.isEnabled()) {
            isButtonDisabled = true;
        }
        Assert.assertFalse(isButtonDisabled);
    }

    @Test
    public void SignIn_05_Login_Success() {
        By emailTextbox = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailTextbox));
        driver.findElement(emailTextbox).clear();
        driver.findElement(emailTextbox).sendKeys("castalk6@gmail.com");

        By passwordTextbox = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(passwordTextbox));
        driver.findElement(passwordTextbox).clear();
        driver.findElement(passwordTextbox).sendKeys("Luu9i20nm@nj");

        explicitWait.until(ExpectedConditions.elementToBeClickable(AppiumBy
                .androidUIAutomator("new UiSelector().text(\"Sign In\")"))).click();

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
