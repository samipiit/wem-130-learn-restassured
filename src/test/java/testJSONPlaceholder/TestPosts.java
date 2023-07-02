package testJSONPlaceholder;

import base.Client;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.util.HashMap;

public class TestPosts extends Client {


    @Test
    public void testPostNewPost() {
        String requestUrl = jsonBaseURL + endpointJsonPosts;

        String title = "Posting using RestAssured!";

        HashMap<Object, Object> jsonBody = new HashMap<>();
        jsonBody.put("userId", 1234);
        jsonBody.put("id", 202);
        jsonBody.put("title", title);
        jsonBody.put("body", "We can automate API interactions using RestAssured");

        ValidatableResponse validatableResponse = post(requestUrl, jsonBody);

        validatableResponse.extract().response().prettyPeek();
        validatableResponse.assertThat().statusCode(HttpURLConnection.HTTP_CREATED).body("title", Matchers.equalToIgnoringCase(title));
    }

}
