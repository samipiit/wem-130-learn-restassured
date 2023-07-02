package testNewsAPI;

import base.Client;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

public class TestEverything extends Client {

    @Test (dataProviderClass = data_providers.NewsAPIDataProviders.class, dataProvider = "testKeywordInTitle")
    public void testKeywordInTitle(String keyword, String scopeOfSearch) {
        Map<String, Object> qParams = new HashMap<>();
        qParams.put("q", keyword);
        qParams.put("searchIn", scopeOfSearch);

        String requestUrl = baseUrl + route + endpointEverything;
        Response response = get(requestUrl, qParams, getApiKey());

        response.then().extract().response().prettyPeek();

        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK).body("articles." + scopeOfSearch, Matchers.everyItem(Matchers.containsStringIgnoringCase(keyword)));
    }
}
