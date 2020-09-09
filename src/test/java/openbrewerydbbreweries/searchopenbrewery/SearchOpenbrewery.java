package openbrewerydbbreweries.searchopenbrewery;

import client.OpenbrewerydbRestClient;
import io.restassured.path.json.JsonPath;
import openbrewerydbbreweries.data.TestData;
import org.testng.annotations.Test;

import java.util.List;

import static client.data.OpenbrewerydbEndpoints.SEARCH_QUERY_URL;
import static io.restassured.RestAssured.get;
import static io.restassured.http.ContentType.JSON;
import static openbrewerydbbreweries.data.TestData.ERROR_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;

public class SearchOpenbrewery {
//    private final OpenbreweryService openbreweryService = new OpenbreweryService();
    private final OpenbrewerydbRestClient openbrewerydbRestClient = new OpenbrewerydbRestClient();

    @Test(dataProvider = "testDataForSearch", dataProviderClass = TestData.class)
    public void shouldSearchWithCorrectQueryParam(String queryParam, String value) {
        List<String> response = openbrewerydbRestClient.getOpenbrewery(queryParam, value);
        assertThat(response).isNotEmpty();
        response.forEach(d ->
                assertThat(d.toLowerCase()).contains(queryParam.toLowerCase()));
    }

    @Test(dataProvider = "testDataForSearchWithInvalidParam", dataProviderClass = TestData.class)
    public void shouldReturnEmptyResponseForInvalidQueryParam(String queryParam, String getValue) {
        List<String> response = openbrewerydbRestClient.getOpenbrewery(queryParam, getValue);
        assertThat(response).isEmpty();
    }

    @Test(dataProvider = "testDataForSearchToCountAmountInResponse", dataProviderClass = TestData.class)
    public void shouldReturnCorrectAmountInResponse(String queryParam, String value, int responseAmount) {
        List<String> response = openbrewerydbRestClient.getOpenbrewery(queryParam, value);
        assertThat(response).isNotEmpty();
        assertThat(response.size()).isEqualTo(responseAmount);
    }

    @Test()
    public void shouldReturnBadRequestForInvalidUrl() {
        String response = openbrewerydbRestClient.getOpenbreweryBadRequest();
        assertThat(response).contains(ERROR_MESSAGE);
    }

    @Test(dataProvider = "testData", dataProviderClass = TestData.class)
    public void shouldGetOpenbrewery(String query) {
        JsonPath body = get(SEARCH_QUERY_URL + query)
                .then()
                .assertThat().contentType(JSON)
                .and()
                .extract()
                .response()
                .jsonPath();
        assertThat(body.getString("[0]")).contains(query);
    }
}
