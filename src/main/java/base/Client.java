package base;

import config.Config;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
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

    public String jsonBaseURL;
    public String endpointJsonPosts;
    public String endpointJsonComments;
    public String endpointJsonAlbums;
    public String endpointJsonPhotos;
    public String endpointJsonUsers;


    @BeforeMethod
    public void loadConfig() {
        config = new Config();

        baseUrl = config.properties.getProperty("base_url");
        route = config.properties.getProperty("route");
        endpointEverything = config.properties.getProperty("everything_endpoint");
        endpointTopHeadlines = config.properties.getProperty("top_headlines_endpoint");
        endpointSources = config.properties.getProperty("sources_endpoint");

        jsonBaseURL = config.properties.getProperty("json_base_url");
        endpointJsonPosts = config.properties.getProperty("posts");
        endpointJsonAlbums = config.properties.getProperty("albums");
        endpointJsonPhotos = config.properties.getProperty("photos");
        endpointJsonComments = config.properties.getProperty("comments");
        endpointJsonUsers = config.properties.getProperty("users");
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

    public ValidatableResponse post(String url, HashMap<Object, Object> body) {
        RestAssured.defaultParser = Parser.JSON;

        return given().contentType(ContentType.JSON).with().body(body).when().post(url).then();
    }

}
