import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestTopHeadlines extends Client {

    @Test
    public void testTopHeadlinesResponseStatusCode() {
        Map<String, Object> qParams = new HashMap<>();
        qParams.put("q", "bitcoin");

        String requestUrl = baseUrl + route + endpointTopHeadlines;
        Response response = get(requestUrl, qParams, getApiKey());

        response.then().extract().response().prettyPeek();

        response.then().assertThat().statusCode(200);
    }

    @Test
    public void testTopHeadlinesSourcesResponse() {
        Map<String, Object> qParams = new HashMap<>();
        qParams.put("sources", "cnn");

        String requestUrl = baseUrl + route + endpointTopHeadlines;
        Response response = get(requestUrl, qParams, getApiKey());

        ArrayList<LinkedHashMap<Object, Object>> articles = response.then().extract().body().path("articles");

        for (LinkedHashMap<Object, Object> article : articles) {
            LinkedHashMap<Object, Object> source = (LinkedHashMap<Object, Object>) article.get("source");

            Assert.assertTrue(source.get("id").toString().equalsIgnoreCase("cnn"));
        }

    }

}
