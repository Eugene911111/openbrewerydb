package client;

import java.util.List;

import static client.data.OpenbrewerydbEndpoints.INVALID_URL;
import static client.data.OpenbrewerydbEndpoints.SEARCH_QUERY_URL;
import static io.restassured.RestAssured.get;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;

public class OpenbrewerydbRestClient {

    public List<String> getOpenbrewery(String queryParam, String value) {
        System.out.println(SEARCH_QUERY_URL + queryParam);
        return get(SEARCH_QUERY_URL + queryParam)
                .then()
                .extract()
                .response()
                .jsonPath()
                .getList(value);
    }

    public String getOpenbreweryBadRequest() {
        return get(INVALID_URL)
                .then()
                .statusCode(SC_NOT_FOUND)
                .extract()
                .asString();
    }
}
