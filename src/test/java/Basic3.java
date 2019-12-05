import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Basic3 {

    @Test
    public void createAndDelete(){

        String b = "{\n" +
                "\n" +
                "    \"location\":{\n" +
                "\n" +
                "        \"lat\" : -38.383494,\n" +
                "\n" +
                "        \"lng\" : 33.427362\n" +
                "\n" +
                "    },\n" +
                "\n" +
                "    \"accuracy\":50,\n" +
                "\n" +
                "    \"name\":\"Frontline house\",\n" +
                "\n" +
                "    \"phone_number\":\"(+91) 983 893 3937\",\n" +
                "\n" +
                "    \"address\" : \"29, side layout, cohen 09\",\n" +
                "\n" +
                "    \"types\": [\"shoe park\",\"shop\"],\n" +
                "\n" +
                "    \"website\" : \"http://google.com\",\n" +
                "\n" +
                "    \"language\" : \"French-IN\"\n" +
                "\n" +
                "}";

        //Task 1 - Grab the response
        //BaseURL
        RestAssured.baseURI = "http://216.10.245.166";

        Response res = given().
                queryParam("key", "qaclick123").
                body(b).
                when().
                post("/maps/api/place/add/json").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("status", equalTo("OK")).
                extract().response();

        String responseString = res.asString();
        System.out.println(responseString);

        //Task 2 - Grab the Place ID from Response
        JsonPath js = new JsonPath(responseString); //convert String to Json
        String placeId = js.get("place_id");
        System.out.println(placeId);

        //Task 3 - Place this id in the Delete
        given().
                queryParam("key","AIzaSyDIQgAh0B4p0SdyYkyW8tlG-y0yJMfss5Y").

                body("{"+
                        "\"place_id\": \""+placeId+"\""+
                        "}").
                when().
                post("/maps/api/place/delete/json").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("status",equalTo("OK"));
    }
}
