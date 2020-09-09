package client.data;

public final class OpenbrewerydbEndpoints {
    public static final String BASE_URL = "https://api.openbrewerydb.org/breweries";
    public static final String SEARCH_URL = BASE_URL + "/search";
    public static final String SEARCH_QUERY_URL = SEARCH_URL + "?query=";
    public static final String INVALID_URL = "https://api.openbrewerydb.org/breweries/searc12h?query=dog";
}
