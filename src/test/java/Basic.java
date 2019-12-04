import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class Basic {

    @Test
    public void postRequest(){
        //BaseURL
        RestAssured.baseURI = "http://216.10.245.166";

        given().
                param("location","-38.383494,33.427362").
                param("key", " qaclick123").
                when().get("/maps/api/place/add/json").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).
                header("Server", equalTo("Apache"));
    }

}
