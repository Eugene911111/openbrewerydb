package openbrewerydbbreweries.data;

import org.testng.annotations.DataProvider;

public class TestData {
    @DataProvider(name = "testDataForSearch")
    public static Object[][] testDataForSearch() {
        return new Object[][]{
                {"Diving Dog Brewhouse", "name"},
                {"1802 Telegraph Ave", "street"},
                {"North Carolina", "state"},
                {"27607-5415", "postal_code"},
                {"http://www.flyingdogbrewery.com", "website_url"}
        };
    }

    @DataProvider(name = "testDataForSearchToCountAmountInResponse")
    public static Object[][] testDataForSearchToCountAmountInResponse() {
        return new Object[][]{
                {"Diving Dog Brewhouse", "name", 1},
                {"1802 Telegraph Ave", "street", 1},
                {"North Carolina", "state", 315},
                {"27607-5415", "postal_code", 1},
                {"http://www.flyingdogbrewery.com", "website_url", 1}
        };
    }

    @DataProvider(name = "testDataForSearchWithInvalidParam")
    public static Object[][] testDataForSearchWithInvalidParam() {
        return new Object[][]{
                {"invalidSearchParam", "name"},
                {"invalidSearchParam", "street"},
                {"invalidSearchParam", "state"},
                {"invalidSearchParam", "postal_code"},
                {"invalidSearchParam", "website_url"},
        };
    }

    @DataProvider(name = "testData")
    public static Object[][] testData() {
        return new Object[][]{
//                {"Diving Dog Brewhouse"},
//                {"1802 Telegraph Ave"},
                {"North Carolina"},
//                {"27607-5415"},
//                {"http://www.flyingdogbrewery.com"}
        };
    }

    public static final String ERROR_MESSAGE = "Couldn't find Brewery with 'id'=searc12h\"";
}
