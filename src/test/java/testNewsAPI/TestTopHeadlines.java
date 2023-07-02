package testNewsAPI;

import base.Client;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.HashMap;
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

        response.then().assertThat().statusCode(200).body("articles.source.id", Matchers.everyItem(Matchers.equalTo("cnn")));

    }

//    @Test
//    public void testTopHeadlinesCountry() {
//        String sourceCountry = "us";
////        String query = "SELECT SOURCE_NAME FROM SOURCES WHERE COUNTRY = '" + sourceCountry + "'";
//
//        ArrayList<Argument> dbSources = new ArrayList<>();
//        dbSources.add(new Argument("The Athletic"));
//        dbSources.add(new Argument("The Washington Post"));
//        dbSources.add(new Argument("USA Today"));
//        dbSources.add(new Argument("Earth.com"));
//        dbSources.add(new Argument("WCPO"));
//        dbSources.add(new Argument("KATU"));
//        dbSources.add(new Argument("YouTube"));
//        dbSources.add(new Argument("Foxweather.com"));
//        dbSources.add(new Argument("Yahoo Entertainment"));
//        dbSources.add(new Argument("Sarasota Herald-Tribune"));
//        dbSources.add(new Argument("Yogajournal.com"));
//        dbSources.add(new Argument("AOL"));
//        dbSources.add(new Argument("CNN"));
//        dbSources.add(new Argument("nj.com"));
//
//        Map<String, Object> qParams = new HashMap<>();
//        qParams.put("country", sourceCountry);
//
//        String requestUrl = baseUrl + route + endpointTopHeadlines;
//        Response response = get(requestUrl, qParams, getApiKey());
//
//        response.then().assertThat().statusCode(200).body(dbSources, Matchers.hasItemInArray("articles.source.name"));
//    }


}
