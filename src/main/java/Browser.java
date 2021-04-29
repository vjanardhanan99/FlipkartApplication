import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

/**
 * This class contains the initial setup for launching the application like opening the browser and closing the browser
 */
public class Browser {

    /**
     * Method that opens the Browser for automating Flipkart application
     * @param browser
     * @return WebDriver object
     */
    @Parameters({"browser"})
    public static WebDriver openBrowser(String browser)
    {
        WebDriver driver = null;
        String baseDirectory=System.getProperty("user.dir");
        if(browser.equalsIgnoreCase("chrome"))
        {
            System.setProperty("webdriver.chrome.driver",baseDirectory+"/src/main/resources/chromedriver.exe");
            ChromeOptions options=new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("disable-infobars");
            driver=new ChromeDriver(options);

            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            driver.navigate().to("https://www.flipkart.com");

            //click on close button of login screen
            driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
        }

        return driver;
    }

    /**
     * Method that closes the current driver instance
     * @param driver
     */
    public static void closeBrowser(WebDriver driver)
    {

        driver.close();
    }
}
