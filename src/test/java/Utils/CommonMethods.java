package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CommonMethods {

    public static WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void OpenBrowser() {
        ConfigReader.readProperties(Constants.CONFIGRATION_FILEPATH);
        switch (ConfigReader.getPropertyValue("browser")) {
            case "chrome":
//               System.setProperty("webdriver.chrome.driver","src/drivers/chromedriver.exe");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
//                System.setProperty("webdriver.gecko.driver","src/drivers/geckodriver.exe");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Invalid name of Browser");

        }
        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);

    }

    public static void sendText(WebElement element, String texttoSend) {
        element.clear();
        element.sendKeys(texttoSend);
    }

    public static WebDriverWait getWait() {

        WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
        return wait;
    }

    public static void waitForClickability(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void click(WebElement element) {
        waitForClickability(element);
        element.click();

    }

    public static JavascriptExecutor getJSExecutor() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Since driver does not belong to JavascriptExecutor we upcast JavascriptExecutor.
        return js;
    }

    public static void jsClick(WebElement element) {
        getJSExecutor().executeScript("arguments[0].click()", element);
    }

    public static void takeScreenshot(String fileName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

        try {
            File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(sourceFile, new File(Constants.SCREENSHOT_FILEPATH + fileName+getTimeStamp("yyyy-MM-dd-HH-mm-ss")+".png"));
        } catch (IOException e) {

        }
    }

//    YY-MM-DD-HH-MM-SS-MLS TIME STAMP Pattern
    public static String getTimeStamp(String pattern){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        return  sdf.format(date);
    }

    ////@Test
////public void test(){
////        System.out.println(System.getProperty("os.name"));
////    System.out.println(System.getProperty("user.name"));
////    System.out.println(System.getProperty("user.dir"));
//
//}
    @AfterMethod(alwaysRun = true)
    public static void teardown() {
        if (driver != null) {
            driver.quit();

        }
    }
}
