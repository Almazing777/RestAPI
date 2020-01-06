import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class JiraAPI {

    Properties prop = new Properties();

    @BeforeTest
    public void getData() throws IOException {
        FileInputStream fis = new FileInputStream("/Users/Almas/Documents/RestAssuredAPITesting/src/test/src/files/env.properties");
        prop.load(fis);
    }


    @Test
    public void JiraAPI() {
        //creating session

        RestAssured.baseURI = prop.getProperty("JIRAHOST");
        Response res = given()
                .header("Content-Type", "application/json")
                .body("{ \"username\": \"mendygax\", \"password\": \"twdAM0519!\"}")
                .when()
                .post("/rest/auth/1/session").then().statusCode(200)
                .extract().response();

        JsonPath js = ReusableMethods.rawToJson(res);
        String sessionid = js.get("session.value");
        System.out.println(sessionid);



    }

}
