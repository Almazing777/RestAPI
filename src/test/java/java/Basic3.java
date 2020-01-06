//package java;
//
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.payLoad;
//import java.resources;
//import java.util.Properties;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.equalTo;
//
//public class Basics3 {
//
//    Properties prop=new Properties();
//    @BeforeTest
//    public void getData() throws IOException
//    {
//
//        FileInputStream fis=new FileInputStream("C:\\Users\\rahul\\workspace\\DemoProject\\src\\files\\env.properties");
//        prop.load(fis);
//
//        //prop.get("HOST");
//    }
//
//    @Test
//    public void AddandDeletePlace()
//    {
//
//        //Task 1- Grab the response
//        RestAssured.baseURI= prop.getProperty("HOST");
//        Response res=given().
//
//                queryParam("key",prop.getProperty("KEY")).
//                body(payLoad.getPostData()).
//                when().
//                post(resources.placePostData()).
//                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
//                body("status",equalTo("OK")).
//                extract().response();
//        // Task 2- Grab the Place ID from response
//
//        String responseString=res.asString();
//        System.out.println(responseString);
//        JsonPath js= new JsonPath(responseString);
//        String placeid=js.get("place_id");
//        System.out.println(placeid);
//
//
//        //Task 3 place this place id in the Delete request
//        given().
//                queryParam("key","AIzaSyDIQgAh0B4p0SdyYkyW8tlG-y0yJMfss5Y").
//
//                body("{"+
//                        "\"place_id\": \""+placeid+"\""+
//                        "}").
//                when().
//                post("/maps/api/place/delete/json").
//                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
//                body("status",equalTo("OK"));
//
//
//    }
//}
