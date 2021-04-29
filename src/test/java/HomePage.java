import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.internal.EclipseInterface;

import java.io.IOException;
import java.util.List;

/**
 * This class contains methods for locating web elements in HomePage
 */
public class HomePage extends BasePage{

    private WebDriver driver;

    public HomePage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
    }

    private By searchBar = By.xpath("//input[@name='q']");
    private By searchButton = By.xpath("//button[@class='L0Z3Pu']");
    private By firstElementForAddToCartScenario = By.xpath("(//a[@class='s1Q9rs'])[1]");
    private By addToCartButton = By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']");
    private By removeFromCartButton = By.xpath("(//div[@class='_3dsJAO'])[2]");
    private By confirmRemoveFromCartButton = By.xpath("//div[@class='_3dsJAO _24d-qY FhkMJZ']");
    private By sortByPriceHighestButton = By.xpath("//div[contains(text(),'Price -- High to Low')]");
    private By firstElementForPincodeScenario = By.xpath("(//a[@class='s1Q9rs'])[1]");
    private By pinCode = By.xpath("//input[@id='pincodeInputId']");
    private By filterByRatingLink = By.xpath("(//div[@class='_3879cV'])[8]");
    private By actualAddress = By.xpath("(//div[@class='_2NKhZn _1U1qnR'])[2]");
    private By topOffersLink = By.xpath("//div[contains(text(),'Top Offers')]");
    private By firstElementForBuyNowScenario = By.xpath("(//div[@class='_4rR01T'])[1]");
    private By buyNowButton = By.xpath("//button[@class='_2KpZ6l _2U9uOA ihZ75k _3AWRsL']");
    private By moreButton = By.cssSelector(".exehdJ");
    private By firstElementForAmountValidation = By.xpath("(//div[@class='CXW8mj _21_khk'])[1]");
    private By searchElementPositiveScenario = By.xpath("//div[@class='_4rR01T']");
    private By AboutField = By.xpath("(//div[@class='_3-dnWo'])[1]");
    private By ContactUsField = By.xpath("//a[contains(text(),'Contact Us')]");
    private By CareersField = By.xpath("//a[contains(text(),'Careers')]");
    private By PressField = By.xpath("//a[contains(text(),'Press')]");
    private By cartElement = By.xpath("//div[@class='CXW8mj']");
    private By emptyCart = By.xpath("//div[@class='_1LCJ1U']");
    private By invalidPincode = By.xpath("//div[@class='_1NQ_ER']");

    /**
     * This method searches for an item in the search bar of the HomePage
     * @param item
     * @throws IOException
     */
    public void searchItem(String item) throws IOException
    {
            try {
                locateElement(searchBar).sendKeys(item);
                locateElement(searchBar).submit();
                click(searchButton);
                Reports.extentTest.log(Status.PASS," verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

            } catch(Exception e) {
                Reports.extentTest.log(Status.FAIL,"not matching",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            }

    }

    /**
     * Verifies if the search item matches the expected item
     * @param searchKey
     * @throws IOException
     */
    public void verifySearchItem(String searchKey) throws IOException {
        try {
            String actualItem = locateElement(searchElementPositiveScenario).getText();
            Assert.assertTrue(actualItem.contains("APPLE iPhone 12 Pro Max"));
            Reports.extentTest.log(Status.PASS," matching", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch(Exception e) {
            Reports.extentTest.log(Status.FAIL,"not matching",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    /**
     * Verifies if an invalid item is searched, no results page is displayed
     */
    public void verifySearchItemNegativeScenario() throws IOException {
        try {
            Assert.assertTrue(locateElement(By.xpath("//div[contains(text(),'Sorry, no results found')]")).getText().contains("Sorry, no results found"));
            Reports.extentTest.log(Status.PASS," matching", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch(Exception e) {
            Reports.extentTest.log(Status.FAIL,"not matching",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    /**
     * This method adds a selected product into the cart on click of add to cart button
     * @throws IOException
     */
    public void addToCart() throws IOException {
        try {
            click(firstElementForAddToCartScenario);
            //Window handle to get the Product details page window
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
            }
            click(addToCartButton);
            Reports.extentTest.log(Status.PASS," verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            Reports.extentTest.log(Status.FAIL,"not verified",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    /**
     * This method verifies if the item is added to cart successfully
     * @throws IOException
     */
    public void verifyAddToCart() throws IOException {
        try {
            List<WebElement> cart = driver.findElements(cartElement);
            Assert.assertTrue(cart.size() == 1);
            Reports.extentTest.log(Status.PASS," verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            Reports.extentTest.log(Status.FAIL,"not verified",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    /**
     * This method removes a product from the cart on click on removefromcart button
     */
    public void removeFromCart() throws IOException {
        try {
            click(removeFromCartButton);
            Reports.extentTest.log(Status.PASS," verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e)
        {
            Reports.extentTest.log(Status.FAIL,"not verified",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    /**
     * This method verifies if the product is removed from cart successfully
     * @throws IOException
     */
    public void verifyRemoveFromCart() throws IOException {
        try {
            Assert.assertTrue(driver.findElement(emptyCart).getText().contains("Missing Cart items?"));
            Reports.extentTest.log(Status.PASS," verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            Reports.extentTest.log(Status.FAIL,"not verified",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    /**
     * This method removes the element from the cart when the confirm pop up is appeared
     * @throws IOException
     */
    public void confirmRemoveFromCart() throws IOException {
        try {
            click(confirmRemoveFromCartButton);
            Reports.extentTest.log(Status.PASS," verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch(Exception e) {
            Reports.extentTest.log(Status.FAIL,"not verified",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    /**
     * This method sorts the elements based on price starting from highest first
     * @throws IOException
     */
    public void sortByPriceHighToLow() throws IOException {
        try {
            click(sortByPriceHighestButton);
            Reports.extentTest.log(Status.PASS," verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch(Exception e) {
            Reports.extentTest.log(Status.FAIL,"not verified",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }

    }

    /**
     * Verifies if the most expensive mobile is displayed first after clicking on the sort by price button
     * @param priceBeforeSorting
     * @throws IOException
     */
    public void verifyExpensiveMobile(String priceBeforeSorting) throws IOException {
        try {

            String priceAfterSorting = locateElement(By.xpath("(//div[@class='_30jeq3'])[1]")).getText();
            int priceBefore = Integer.parseInt(priceBeforeSorting);
            int priceAfter = Integer.parseInt(priceAfterSorting);
            Assert.assertTrue(!(priceAfterSorting.equals(priceBeforeSorting)));
            Assert.assertTrue(priceAfter > priceBefore);
            Reports.extentTest.log(Status.PASS," verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch(Exception e) {
            Reports.extentTest.log(Status.FAIL,"not verified",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    /**
     * This method enters an invalid pincode in the pincode field
     */
    public void enterInvalidPincode() throws IOException {
        try {
            click(firstElementForPincodeScenario);
            for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
            }
            locateElement(pinCode).sendKeys("76975689");
            locateElement(pinCode).submit();
            Reports.extentTest.log(Status.PASS," verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch(Exception e) {
            Reports.extentTest.log(Status.FAIL,"not verified",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }

    }

    /**
     * Verifies if the pincode entered is valid or not
     * @throws IOException
     */
    public void verifyInvalidPincode() throws IOException {
        try {
            Assert.assertTrue(locateElement(invalidPincode).getText().contains("Not a valid pincode"));
            Reports.extentTest.log(Status.PASS," verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch(Exception e) {
            Reports.extentTest.log(Status.FAIL,"not verified",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    /**
     * Verifies that the results are displayed after applying the filter
     * @param text1
     * @throws IOException
     */
    public void verifyFilterValue(String text1) throws IOException {
        try {
            String text2 = locateElement(By.xpath("//span[@class='_10Ermr']")).getText();
            System.out.println(text2);
            Assert.assertTrue(locateElement(By.xpath("//div[@class='_3sckoD']")).isDisplayed());
            Reports.extentTest.log(Status.PASS," verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            Reports.extentTest.log(Status.FAIL,"not verified",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    /**
     * This method enters a valid pincode in the pincode field
     * @throws IOException
     */
    public void enterValidPincode() throws IOException {
        try {
            click(firstElementForPincodeScenario);
            for(String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
            }
            locateElement(pinCode).sendKeys("695008");
            locateElement(pinCode).submit();
            Reports.extentTest.log(Status.PASS," verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch(Exception e) {
            Reports.extentTest.log(Status.FAIL,"not verified",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }

    }

    /**
     * This method verifies if the success message is displayed for valid pincode
     */
    public void verifyValidPincode() throws IOException {
        try {
            Assert.assertTrue(locateElement(By.xpath("//span[@class='_2P_LDn']")).isDisplayed());
            Assert.assertTrue(locateElement(By.xpath("//span[@class='_2P_LDn']")).isEnabled());
            String successMessage = locateElement(By.xpath("//div[@class='_3XINqE']")).getText();
            Assert.assertTrue(successMessage.contains("Delivery"));
            Reports.extentTest.log(Status.PASS," verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            Reports.extentTest.log(Status.FAIL,"not verified",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    /**
     * This method verifies if user is asked to login if user clicks on Buy Now for a product
     */
    public void verifyIfUserPromptedToLogin() throws IOException {
        try {
            Assert.assertTrue(locateElement(By.xpath("//h3[@class='_1hUz_t _2-K-ro']")).getText().contains("LOGIN OR SIGNUP"));
            Reports.extentTest.log(Status.PASS," verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            Reports.extentTest.log(Status.FAIL,"not verified",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    /**
     * Verifies if all the sections in the footer text are displayed correctly
     */
    public void verifyFooterText() throws IOException {
        try {
            String About = locateElement(AboutField).getText();
            Assert.assertEquals(About, "ABOUT");

            String ContactUs = locateElement(ContactUsField).getText();
            Assert.assertEquals(ContactUs, "Contact Us");

            String Careers = locateElement(CareersField).getText();
            Assert.assertEquals(Careers, "Careers");

            String Press = locateElement(PressField).getText();
            Assert.assertEquals(Press, "Press");
            Reports.extentTest.log(Status.PASS," verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            Reports.extentTest.log(Status.FAIL,"not verified",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    /**
     * This method clicks on the filter by rating 4* and above
     */
    public void clickFilterByRating() throws IOException {
        try {
            click(filterByRatingLink);
            Reports.extentTest.log(Status.PASS," verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch(Exception e) {
            Reports.extentTest.log(Status.FAIL,"not verified",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    /**
     * Fetches the actual address from flipkart application homepage
     * @return the actual address String object
     * @throws IOException
     */
    public String verifyActualAddress() throws IOException {
            return locateElement(actualAddress).getText();
    }

    /**
     * This method verifies with the registered address of Flipkart matches with the actual address
     * @param expectedAddress
     * @throws IOException
     */
    public void verifyRegisteredAddressOfFlipkart(String expectedAddress) throws IOException {
        try {
            String actualAddressFound = locateElement(actualAddress).getText();
            Assert.assertEquals(actualAddressFound, expectedAddress);
            Reports.extentTest.log(Status.PASS," verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            Reports.extentTest.log(Status.FAIL,"not verified",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    /**
     * This method clicks on top offers link
     * @throws IOException
     */
    public void clickTopOffers() throws IOException {
        try {
            click(topOffersLink);
            Reports.extentTest.log(Status.PASS," verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            Reports.extentTest.log(Status.FAIL,"not verified",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    /**
     * This method verifies if all the category titles are displayed on clicking the Top Offers link
     */
    public void verifyTopOffers() throws IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='_2cAig-']")));
            List<WebElement> headers = driver.findElements(By.xpath("//h2[@class='_2cAig-']"));
            Assert.assertTrue(headers.size() > 0);
            for (int i = 0; i < headers.size(); i++) {
                System.out.println(headers.get(i).getText());
            }
            Reports.extentTest.log(Status.PASS," verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            Reports.extentTest.log(Status.FAIL,"not verified",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    /**
     * This method clicks on a product link and clicks on Buy Now button
     * @throws IOException
     */
    public void clickOnAProductAndClickBuyNow() throws IOException {
        try {
            click(firstElementForBuyNowScenario);
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
            }
            click(buyNowButton);
            Reports.extentTest.log(Status.PASS," verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            Reports.extentTest.log(Status.FAIL,"not verified",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    /**
     * This method clicks on more button of home page
     * @throws IOException
     */
    public void clickMoreButton() throws IOException {
        try {
            click(moreButton);
            Reports.extentTest.log(Status.PASS," verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            Reports.extentTest.log(Status.FAIL,"not verified",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    /**
     * Verifies if all the options under menu dropdown are displayed
     */
    public void verifyMoreOptions() throws IOException {
        try {
            List<WebElement> menu = driver.findElements(By.className("_3vhnxf"));
            Assert.assertTrue(menu.size() > 0);
            for (int i = 0; i < menu.size(); i++) {
                System.out.println(menu.get(i).getText());
            }
            Reports.extentTest.log(Status.PASS," verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            Reports.extentTest.log(Status.FAIL,"not verified",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    /**
     * This method selects a product and adds it to cart
     * @throws IOException
     */
    public void selectAProductAndAddToCart() throws IOException {
        try {
            click(firstElementForAmountValidation);
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
            }
            click(addToCartButton);
            Reports.extentTest.log(Status.PASS," verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            Reports.extentTest.log(Status.FAIL,"not verified",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    /**
     * This method verifies the totalamount in cart for a single product
     * @throws IOException
     */
    public void verifyTotalAmountInCartForSingleProduct() throws IOException {
        try {
            String TotalAmount = locateElement(By.xpath("//div[@class='Ob17DV _3X7Jj1']")).getText();
            System.out.println(TotalAmount);
            Assert.assertTrue(!(TotalAmount.isEmpty()));
            Reports.extentTest.log(Status.PASS," verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch(Exception e) {
            Reports.extentTest.log(Status.FAIL,"not verified",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }

    }

    /**
     * Verifies total amount in cart for multiple products
     */
    public void verifyTotalAmountInCartForMultipleProducts(String totalAmount1) throws IOException {
        try {
            String totalAmount2 = locateElement(By.xpath("((//div[@class='_24KATy'])[5])/following::span[1]")).getText();
            System.out.println("totalAmount2 = " + totalAmount2);

            Assert.assertTrue(Integer.parseInt(totalAmount2) > Integer.parseInt(totalAmount1));
            Reports.extentTest.log(Status.PASS," verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            Reports.extentTest.log(Status.FAIL,"not verified",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }


}
