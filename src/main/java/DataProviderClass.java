import org.testng.annotations.DataProvider;

/**
 * This class contains the data that are to be passed to the Test methods in FlipkartTestCases class
 */
public class DataProviderClass {

    @DataProvider(name="PositiveSearchDataProvider")
    public static Object[][] getDataForPositiveSearch()
    {

        return new Object[][] {{"iphone 12 pro max"}};
    }

    @DataProvider(name="NegativeSearchDataProvider")
    public static Object[][] getDataForNegativeSearch()
    {

        return new Object[][] {{"1457543465643"}};
    }

    @DataProvider(name="AddToCart")
    public static Object[][] getDataForAddToCart()
    {

        return new Object[][] {{"iphone"}};
    }

    @DataProvider(name="VerifyBrandFilter")
    public static Object[][] getDataForVerifyBrandFilter()
    {

        return new Object[][] {{"T-shirts"}};
    }

    @DataProvider(name="VerifyRegisteredAddress")
    public static Object[][] getDataForVerifyRegisteredAddress()
    {
        return new Object[][] {{"Flipkart Internet Private Limited,\n" +
                "Buildings Alyssa, Begonia &\n" +
                "Clove Embassy Tech Village,\n" +
                "Outer Ring Road, Devarabeesanahalli Village,\n" +
                "Bengaluru, 560103,\n" +
                "Karnataka, India\n" +
                "CIN : U51109KA2012PTC066107\n" +
                "Telephone: 1800 202 9898"}};
    }

    @DataProvider(name="VerifyBuyNow")
    public static Object[][] getDataForBuyNow()
    {

        return new Object[][] {{"APPLE iPhone 11"}};
    }

    @DataProvider(name="VerifyTotalAmountInCart")
    public static Object[][] getDataForVerifyTotalAmountInCartSingleProduct()
    {

        return new Object[][] {{"clock"}};
    }

    @DataProvider(name="VerifyExpensiveMobile")
    public static Object[][] getDataForVerifyExpensiveMobile()
    {

        return new Object[][] {{"iphone"}};
    }

}
