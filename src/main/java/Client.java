import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Client {

    public Config config;
    public String baseUrl;
    public String route;
    public String endpointEverything;
    public String endpointTopHeadlines;
    public String endpointSources;
    private String apiKey;

    @BeforeMethod
    public void loadConfig() {
        config = new Config();

        baseUrl = config.properties.getProperty("base_url");
        route = config.properties.getProperty("route");
        endpointEverything = config.properties.getProperty("everything_endpoint");
        endpointTopHeadlines = config.properties.getProperty("top_headlines_endpoint");
        endpointSources = config.properties.getProperty("sources_endpoint");

    }

    public String getApiKey() {
        if (config != null && apiKey == null) {
            apiKey = config.properties.getProperty("api_key");
        }
        return apiKey;
    }

    public Response get(String url){
        RestAssured.defaultParser = Parser.JSON;

        return given().when().get(url).then().contentType(ContentType.JSON).extract().response();
    }

    public Response get(String url, String apiKey) {
        RestAssured.defaultParser = Parser.JSON;

        Header authHeader = new Header("x-Api-Key", apiKey);

        return given().header(authHeader).when().get(url).then().contentType(ContentType.JSON).extract().response();
    }

    public Response get(String url, Map<String, Object> queryParams, String apiKey) {
        RestAssured.defaultParser = Parser.JSON;

        Header authHeader = new Header("x-Api-Key", apiKey);

        return given().header(authHeader).queryParams(queryParams).when().get(url).then().contentType(ContentType.JSON).extract().response();
    }

}
