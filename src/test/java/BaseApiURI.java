//
//import io.restassured.RestAssured;
//import org.hamcrest.Matchers;
//import org.testng.annotations.BeforeClass;
//
//public class BaseApiURI {
//
//    @BeforeClass
//    public void baseURI() {
//        RestAssured.baseURI = "https://staging-api.deens.com";
//    }
//}
//
//API USERS TESTS
//
//        package io.testpro.deens.apiTests;
//
//        import io.restassured.response.Response;
//        import io.testpro.deens.apiURI.BaseApiURI;
//        import org.hamcrest.Matchers;
//        import org.testng.annotations.BeforeClass;
//        import org.testng.annotations.Test;
//
//        import java.sql.Timestamp;
//
//        import static io.restassured.RestAssured.given;
//
//public class Api_UsersTests_AK extends BaseApiURI {
//
//    //    =================== REGISTER TESTS (AK) ====================
//    @Test
//    public void usersPostRegisterSuccessTest() {
//        Response response = given()
//                .body("{\"username\": \""+ generateUsername() +"\", \"email\": \""+ generateEmail() +"\", \"password\": \"deens333\"}")
//                .when().post("/users/signup");
//        response.then().body("externalProvider", Matchers.is("auth0"));
//        response.then().statusCode(200);
//    }
//
//    public String generateEmail() {
//        long timeStamp = getTimestamp();
//        String email = "akogay+test" + timeStamp + "@testpro.io";
//        return email;
//    }
//    public String generateUsername() {
//        long timeStamp = getTimestamp();
//        String username = "AK" + timeStamp;
//        return username;
//    }
//
//    public long getTimestamp() {
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        return timestamp.getTime();
//    }
//
//
//    @Test
//    public void usersPostRegisterExistingEmailTest() {
//        Response response = given()
//                .body("{\"username\": \"akogay+test3\", \"email\": \"akogay+test3@testpro.io\", \"password\": \"deens333\"}")
//                .when().post("/users/signup");
//        response.then().statusCode(400);
//        response.then().body("message", Matchers.is("This email address is already being used"));
//    }
//
//    @Test
//    public void usersPostRegisterUsernameOverMaxCharactersTest() {
//        Response response = given()
//                .body("{\"username\": \"AK33333333333333333\", \"email\": \"akogay+test7@testpro.io\", \"password\": \"deens333\"}")
//                .when().post("/users/signup");
//        response.then().statusCode(400);
//        response.then().body("message", Matchers.endsWith("Username should have between 1 and 15 characters."));
//    }
//
//    @Test
//    public void usersPostRegisterEmptyUsernameTest() {
//        Response response = given()
//                .body("{\"username\": \"\", \"email\": \"akogay+test4@testpro.io\", \"password\": \"deens333\"}")
//                .when().post("/users/signup");
//        response.then().statusCode(400);
//        response.then().body("message", Matchers.is("username is required"));
//    }
//
//    @Test
//    public void usersPostRegisterInvalidPasswordTest() {
//        Response response = given()
//                .body("{\"username\": \"akogay+test4\", \"email\": \"akogay+test4@testpro.io\", \"password\": \"deens\"}")
//                .when().post("/users/signup");
//        response.then().statusCode(400);
//        response.then().rootPath("{verified=false, rules=[{code=lengthAtLeast, format=[6], verified=false, message=At least %d characters in length}]}");
//    }
//
//
//    //    =================== LOGIN TESTS (AK) ====================
//    @Test
//    public void usersPostLoginSuccessTest() {
//        Response response = given()
//                .body("{\"username\": \"akogay+test1@testpro.io\", \"password\": \"371Ri77Ko82\"}")
//                .when().post("/users/login");
//        response.then().statusCode(200);
//        response.then().body("token_type", Matchers.is("Bearer"));
//    }
//
//    @Test
//    public void usersPostEmptyPasswordLoginTest() {
//        Response response = given()
//                .body("{\"username\": \"akogay+test1@testpro.io\", \"password\": \"\"}")
//                .when().post("/users/login");
//        response.then().statusCode(400);
//        response.then().body("message", Matchers.is("password is required"));
//    }
//
//    @Test
//    public void usersPostEmptyUsernameLoginTest() {
//        Response response = given()
//                .body("{\"username\": \"\", \"password\": \"371Ri77Ko82\"}")
//                .when().post("/users/login");
//        response.then().statusCode(400);
//        response.then().body("message", Matchers.is("username is required"));
//    }
//
//    @Test
//    public void usersPostWrongUsernameLoginTest() {
//        Response response = given()
//                .body("{\"username\": \"akogay+test1@testpro\", \"password\": \"371Ri77Ko82\"}")
//                .when().post("/users/login");
//        response.then().statusCode(404);
//        response.then().body("message", Matchers.is("no user found with username: akogay+test1@testpro"));
//    }
//
//    @Test
//    public void usersPostWrongPasswordLoginTest() {
//        Response response = given()
//                .body("{\"username\": \"akogay+test1@testpro.io\", \"password\": \"333333\"}")
//                .when().post("/users/login");
//        response.then().statusCode(403);
//        response.then().body("message", Matchers.is("Wrong email or password."));
//    }
//
//
//    //    =================== GET USER BY ID TESTS (AK) ====================
//    @Test
//    public void usersGetIdSuccessTest() {
//        Response response = given()
//                .when().get("/users/5cdb7d0255462304d2e89f8c");
//        response.then().statusCode(200);
//        response.then().body("_id", Matchers.is("5cdb7d0255462304d2e89f8c"));
//        response.then().body("username", Matchers.is("akogay+test1"));
//    }
//
//    @Test
//    public void usersGetEmptyIdTest() {
//        Response response = given()
//                .when().get("/users/");
//        response.then().statusCode(403);
//        response.then().body("success", Matchers.is(false));
//        response.then().body("errorMessage", Matchers.is("Missing Authentication Token"));
//    }
//
//    @Test
//    public void usersGetInvalidIdTest() {
//        Response response = given()
//                .when().get("/users/5cdb7d0255462304d2e89");
//        response.then().statusCode(400);
//        response.then().body("message", Matchers.is("userId do not look like a valid id"));
//    }
//
//    //    =================== GET ME TESTS (AK) ====================
//    String accessToken;
//
//    @BeforeClass
//    public void setUp() {
//        Response response = given()
//                .body("{\"username\": \"akogay+test1@testpro.io\",\"password\": \"371Ri77Ko82\"}")
//                .when()
//                .post("https://staging-api.deens.com/users/login");
//        accessToken = "Bearer " + response.then().extract().path("access_token");
//    }
//
//    @Test
//    public void UsersGetMeSuccessTest() {
//        Response response = given().header("Authorization", accessToken)
//                .when().get("https://staging-api.deens.com/users/me");
//        response.then().statusCode(200);
//        response.then().body("email", Matchers.is("akogay+test1@testpro.io"));
//        response.then().body("myReferralCode", Matchers.is("CcI3VEQYg"));
//    }
//
//    @Test
//    public void UsersGetMeIncorrectTokenTest() {
//        Response response = given().header("Authorization", "Incorrect token")
//                .when().get("https://staging-api.deens.com/users/me");
//        response.then().statusCode(403);
//        response.then().body("authorizerError", Matchers.is("Authorization header not passed"));
//        response.then().body("errorMessage", Matchers.is("User is not authorized to access this resource with an explicit deny"));
//    }
//
//    @Test
//    public void UsersGetMeMissingTokenTest() {
//        Response response = given()
//                .when().get("https://staging-api.deens.com/users/me");
//        response.then().statusCode(401);
//        response.then().body("authorizerError", Matchers.is(""));
//        response.then().body("errorMessage", Matchers.is("Unauthorized"));
//    }
//}
//
//
//
//
//=======================================================================================================================
//
//
//        POSTMAN REGISER CREDENTIALS
//
//        {
//        "username": "akogay+test4",
//        "email": "akogay+test4@testpro.io",
//        "password": "deens333"
//        }
//
//        BODY:
//        {
//        "kycValidated": 0,
//        "plsBalance": 0,
//        "level": "regular",
//        "rating": {
//        "average": 0,
//        "count": 0
//        },
//        "whitelistedIcoAddresses": [],
//        "_id": "5ce0456a0ac3591d6794bdf8",
//        "externalUserId": "5ce04569384b220eb44add97",
//        "externalProvider": "auth0",
//        "email": "akogay+test4@testpro.io",
//        "username": "akogay+test4",
//        "myReferralCode": "uS2dyUsyQ",
//        "createdAt": "2019-05-18T17:48:26.043Z",
//        "updatedAt": "2019-05-18T17:48:26.043Z",
//        "__v": 0
//        }