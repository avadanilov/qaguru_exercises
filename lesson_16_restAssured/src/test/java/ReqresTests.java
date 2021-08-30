import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresTests {

    /*
    https://reqres.in/api/login
    {
        "email": "eve.holt@reqres.in",
            "password": "cityslicka"
    }

    Response
200

    {
        "token": "QpwL5tke4Pnpja7X4"
    }
    */

    @Test
    void successfulLogin() {
        given().contentType(JSON)
                .body("{\"email\": \"eve.holt@reqres.in\",\"password\": \"cityslicka\"}")
                .when()
                .post(" https://reqres.in/api/login")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void negativeLogin() {
        given().contentType(JSON)
                .body("{\"email\": \"eve.holt@reqres.in\"}")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }


}
