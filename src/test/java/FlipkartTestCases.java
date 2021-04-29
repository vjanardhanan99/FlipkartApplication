import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This class contains the test methods for testing the automation of Flipkart application
 */
@Listeners(ListenerClass.class)
public class FlipkartTestCases {

    private WebDriver driver;

    /**
     * Method that opens the chrome browser for Flipkart URL
     * @param browser
     */
    @Parameters({"browser"})
    @BeforeMethod
    public void openBrowser(String browser) {

        driver = Browser.openBrowser(browser);
    }

    /**
     * Test method that verifies whether the search results displays only the items corresponding to the search
     * input iphone 12 pro max
     * @param searchKey = iphone 12 pro max
     * @throws IOException
     */
    @Test(dataProvider = "PositiveSearchDataProvider", dataProviderClass = DataProviderClass.class)
    public void verifySearchItemPositive(String searchKey) throws IOException {
        //GIVEN: The Flipkart application is launched
        Reports.createTest("verifySearchItemPositive");
        HomePage homePage = new HomePage(driver);

        //WHEN: An item is searched by clicking on Search button
        homePage.searchItem(searchKey);

        //THEN: Search results corresponding to the item are displayed
        homePage.verifySearchItem(searchKey);
    }

    /**
     * Test method that verifies that for an invalid input in the search, it displays the No Results found Page
     * @param searchKey = 1457543465643
     * @throws IOException
     */
    @Test(dataProvider = "NegativeSearchDataProvider", dataProviderClass = DataProviderClass.class)
    public void verifySearchItemNegative(String searchKey) throws IOException {
        //GIVEN: The Flipkart application is launched
        Reports.createTest("verifySearchItemNegative");
        HomePage homePage = new HomePage(driver);

        //WHEN: An invalid item is searched by clicking on Search button
        homePage.searchItem(searchKey);

        //THEN: No results found page is displayed
        homePage.verifySearchItemNegativeScenario();
    }

    /**
     * Test Method that verifies that when the AddToCart button is clicked, the product is present in the Cart
     * @param searchKey = iphone
     * @throws IOException
     */
    @Test(dataProvider = "AddToCart", dataProviderClass = DataProviderClass.class)
    public void validateAddItemToCart(String searchKey) throws IOException{
        //GIVEN: The Flipkart application is launched
        Reports.createTest("validateAddItemToCart");
        HomePage homePage = new HomePage(driver);

        //WHEN: An item is searched and a product is clicked and from the product page the ADDTOCART button is clicked
        homePage.searchItem(searchKey);
        homePage.addToCart();

        //THEN: The cart should contain the one item which is added
        homePage.verifyAddToCart();
    }

    /**
     * Test method that verifies that when the Remove from cart button is clicked, the product is not present in the cart anymore
     * @param searchKey = iphone
     * @throws IOException
     */
    @Test(dataProvider = "AddToCart", dataProviderClass = DataProviderClass.class)
    public void validateRemoveItemFromCart(String searchKey) throws IOException{
        //GIVEN: The Flipkart application is launched
        Reports.createTest("validateRemoveItemFromCart");
        HomePage homePage = new HomePage(driver);

        //WHEN: An item is searched and a product is clicked and from the product page the ADDTOCART button is clicked and the added product is removed from the cart by clicking on Remove button
        homePage.searchItem(searchKey);
        homePage.addToCart();

        //removefromcart button
        homePage.removeFromCart();
        //confirm remove button
        homePage.confirmRemoveFromCart();

        //THEN: The cart should not contain any items
        homePage.verifyRemoveFromCart();
    }

    /**
     * Test method that verifies whether the most expensive mobile is displayed first after clicking on the Sort by Price High to Low
     *
     * @param searchKey = iphone
     * @throws IOException
     */
    @Test(dataProvider = "VerifyExpensiveMobile", dataProviderClass = DataProviderClass.class)
    public void verifyMostExpensiveMobile(String searchKey) throws IOException, InterruptedException {
        //GIVEN: The Flipkart application is launched
        Reports.createTest("verifyMostExpensiveMobile");
        HomePage homePage = new HomePage(driver);

        //WHEN: an item 'iphone 12 pro max' is searched and the filter Sort by Price High to Low is clicked
        homePage.searchItem(searchKey);
        String priceBeforeSorting = homePage.locateElement(By.xpath("(//div[@class='_30jeq3'])[1]")).getText();
        // Sort button
        homePage.sortByPriceHighToLow();

        //THEN: the element with highest price is displayed first
        homePage.verifyExpensiveMobile(priceBeforeSorting);
    }

    /**
     * Test method that verifies that the error message is displayed when an invalid pincode is entered
     * @param searchKey = iphone, pincode = 76975689
     * @throws IOException
     */
    @Test(dataProvider = "AddToCart", dataProviderClass = DataProviderClass.class)
    public void verifyInvalidPincode(String searchKey) throws IOException {
        //GIVEN: The Flipkart application is launched
        Reports.createTest("verifyInvalidPincode");
        HomePage homePage = new HomePage(driver);

        //WHEN: an invalid pincode is added on the product details page
        homePage.searchItem(searchKey);
        homePage.enterInvalidPincode();

        //THEN: an Error message is displayed saying that the pincode is invalid
        homePage.verifyInvalidPincode();
    }

    /**
     * Test Method that verifies whether the products related to the filter are only displayed when a filter is applied
     * @param searchKey = T-shirts
     * @throws IOException
     */
    @Test(dataProvider = "VerifyBrandFilter", dataProviderClass = DataProviderClass.class)
    public void verifyFilter(String searchKey) throws IOException, InterruptedException {
        //GIVEN: The Flipkart application is launched
        Reports.createTest("verifyBrandFilter");
        HomePage homePage = new HomePage(driver);

        //WHEN: a product is searched and a filter is selected
        homePage.searchItem(searchKey);
        List<WebElement> results = driver.findElements(By.xpath("//div[@class='_312yBx SFzpgZ']"));
        int sizeBefore = results.size();
        System.out.println(sizeBefore);
        String text1 = homePage.locateElement(By.xpath("//span[@class='_10Ermr']")).getText();
        System.out.println(text1);

        homePage.clickFilterByRating();

        //THEN: the filter is applied and only the associated products are displayed
        homePage.verifyFilterValue(text1);
    }

    /**
     * Test method that verifies whether the Registered Address of flipkart is displayed at the bottom of the home page
     * @param expectedAddress
     * @throws IOException
     */
    @Test(dataProvider = "VerifyRegisteredAddress", dataProviderClass = DataProviderClass.class)
    public void verifyRegisteredAddress(String expectedAddress) throws IOException {
        //GIVEN: The Flipkart application is launched
        Reports.createTest("verifyRegisteredAddress");
        HomePage homePage = new HomePage(driver);

        //WHEN: It is scrolled to the bottom of the page
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");

        //THEN: The Registered Address is displayed
        homePage.verifyRegisteredAddressOfFlipkart(expectedAddress);
    }

    /**
     * Test method that verifies all the category texts under the Top Offers link are displayed
     * @throws IOException
     */
    @Test()
    public void verifyOffers() throws InterruptedException, IOException {
        //GIVEN: The Flipkart application is launched
        Reports.createTest("verifyOffers");
        HomePage homePage = new HomePage(driver);

        //WHEN: The Top Offers link is clicked
        homePage.clickTopOffers();

        //THEN: All the Offer category titles exists and are captured
        homePage.verifyTopOffers();
    }

    /**
     * Test method that verifies if correct pincode is entered, then the Delivery by text is displayed
     * @param searchKey = iphone, pincode = 695008
     * @throws IOException
     */
    @Test(dataProvider = "AddToCart", dataProviderClass = DataProviderClass.class)
    public void verifyPincode(String searchKey) throws IOException {
        //GIVEN: The Flipkart application is launched
        Reports.createTest("verifyPincode");
        HomePage homePage = new HomePage(driver);

        //WHEN: an item is searched and in the product details page, enter a valid pincode
        homePage.searchItem(searchKey);
        homePage.enterValidPincode();

        //THEN: The Check button is enabled and the message 'Delivery In' number of days is displayed
        homePage.verifyValidPincode();
    }

    /**
     * Test method that verifies that if BuyNow button is clicked, user is asked to login or signup
     * @param searchKey = APPLE iPhone 11
     * @throws IOException
     */
    @Test(dataProvider = "VerifyBuyNow", dataProviderClass = DataProviderClass.class)
    public void verifyLoginAfterClickingBuyNow(String searchKey) throws IOException {
        //GIVEN: The Flipkart application is launched
        Reports.createTest("verifyLoginAfterClickingBuyNow");
        HomePage homePage = new HomePage(driver);

        //WHEN: An item is searched and the BuyNow button is clicked
        homePage.searchItem(searchKey);
        homePage.clickOnAProductAndClickBuyNow();

        //THEN: User is asked to login or signup
        homePage.verifyIfUserPromptedToLogin();
    }

    /**
     * Test method that verifies the footer text in the home page
     * @throws IOException
     */
    @Test()
    public void footerTextValidation() throws IOException {
        //GIVEN: The Flipkart application is launched
        Reports.createTest("footerTextValidation");
        HomePage homePage = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 20);

        //WHEN: Scroll to the bottom of the page
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");

        //THEN: About, ContactUs, Careers, Press are listed in the footer section
       homePage.verifyFooterText();
    }

    /**
     * Test method that verifies that the more menu is visible in the home page and all the options in the menu are displayed
     * @throws IOException
     */
    @Test()
    public void validateMoreOption() throws IOException {
        //GIVEN: The Flipkart application is launched
        Reports.createTest("validateMoreOption");
        HomePage homePage = new HomePage(driver);

        //WHEN: The More button is clicked
        homePage.clickMoreButton();

        //THEN: All the options available under Menu are displayed
        homePage.verifyMoreOptions();
    }

    /**
     * Test method that verifies that the total amount in cart is accurate when one product is added  to cart
     * @param searchKey = clock
     * @throws IOException
     */
    @Test(dataProvider = "VerifyTotalAmountInCart", dataProviderClass = DataProviderClass.class)
    public void validateTotalAmountInCartForSingleProduct(String searchKey) throws IOException {
        //GIVEN: The Flipkart application is launched
        Reports.createTest("validateTotalAmountInCartForSingleProduct");
        HomePage homePage = new HomePage(driver);

        //WHEN: A Product is added to cart by clicking on the AddToCart Button
        homePage.searchItem(searchKey);
        homePage.selectAProductAndAddToCart();

         //THEN: The total amount should be displayed accurately
        homePage.verifyTotalAmountInCartForSingleProduct();
    }

    /**
     * Test method that verifies that the total amount in cart is accurate when multiple products are added to cart
     * @param searchKey = clock
     * @throws IOException
     */
    @Test(dataProvider = "VerifyTotalAmountInCart", dataProviderClass = DataProviderClass.class)
    public void validateTotalAmountInCartForMultipleProducts(String searchKey) throws IOException, InterruptedException {
        //GIVEN: The Flipkart application is launched
        Reports.createTest("validateTotalAmountInCartForMultipleProducts");
        HomePage homePage = new HomePage(driver);

        //WHEN: a product is added to cart and returned to home page and another product is also added to cart
        homePage.searchItem(searchKey);
        homePage.selectAProductAndAddToCart();
        String totalAmount1 = homePage.locateElement(By.xpath("((//div[@class='_24KATy'])[5])/following::span[1]")).getText();
        System.out.println("totalAmount1= " + totalAmount1);
        //switch to parent window
        ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTb.get(0));
        System.out.println("Page title of parent window: " + driver.getTitle());

        homePage.searchItem(searchKey);
        homePage.selectAProductAndAddToCart();

        //THEN: The total amount is the sum of the two selected products
        homePage.verifyTotalAmountInCartForMultipleProducts(totalAmount1);

    }

    @AfterMethod
    public void closeBrowser() {

        Browser.closeBrowser(driver);
    }
}
