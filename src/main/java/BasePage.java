import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

/**
 * This class contains the general methods for locating the web elements in test cases
 */
public class BasePage {

    public WebDriver driver;

    /**
     * Constructor of BasePage
     * @param driver
     */
    public BasePage(WebDriver driver) {

        this.driver = driver;
    }

    /**
     * Method to take screenshot and saves the screenshot in the given path
     * @return the filename where the screenshot is saved
     * @throws IOException
     */
    public String takeScreenshot() throws IOException {

        File screenshot= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        Random random=new Random();
        String fileName="Screenshot"+random.nextInt(10000);

        Files.move
                (Paths.get(screenshot.getAbsolutePath()),
                        Paths.get(System.getProperty("user.dir")+"/report/"+fileName+".png"));
        return  fileName+".png";
    }

    /**
     * Locates the Webelement by incorporating explicit wait condition
     * @param locator
     * @return WebElement object
     */
    public WebElement locateElement(By locator){

        WebDriverWait wait=new WebDriverWait(driver,20);
        driver.findElement(locator);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));

    }


    /**
     * Method to click on a link after locating the webelement
     * @param locator
     */
    public void click(By locator){

        locateElement(locator).click();
        Reports.extentTest.log(Status.INFO,"Selected value "+ locator);
    }

    /**
     * Method to get the text from an attribute
     * @param locator
     * @return STring object
     */
    public String getText(By locator){
        return locateElement(locator).getText();
    }

}
