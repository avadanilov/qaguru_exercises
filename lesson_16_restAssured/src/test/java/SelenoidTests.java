import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelenoidTests {

    //https://selenoid.autotests.cloud/status
    //assert total 20
  /*  {
        "total": 20,
            "used": 0,
            "queued": 0,
            "pending": 0,
            "browsers": {
        "chrome": {
            "90.0": {},
            "91.0": {}
        },
        "firefox": {
            "88.0": {},
            "89.0": {}
        },
        "opera": {
            "76.0": {},
            "77.0": {}
        }
    }
    }
    */

    @Test
    void checkTotal20() {
        given()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(20));
    }

    @Test
    void checkTotal20WithoutGiven() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(20));
    }

    @Test
    void checkTotal20DontDoThis() {
        Integer response = get("https://selenoid.autotests.cloud/status")
                .then()
                .extract().path("total");
        System.out.println(response);
        assertEquals(20, response);
    }

    @Test
    void checkTotal20AboutResponse() {
        Response response = get("https://selenoid.autotests.cloud/status")
                .then()
                .extract().response();
        System.out.println("simple: " + response);
        System.out.println("toString: " + response.toString());
        System.out.println("asString: " + response.asString());
        System.out.println("total: " + response.path("total") + "");
        System.out.println("browsers: " + response.path("browsers.chrome").toString());
/*
simple: io.restassured.internal.RestAssuredResponseImpl@65760be3
toString: io.restassured.internal.RestAssuredResponseImpl@65760be3
asString: {"total":20,"used":0,"queued":0,"pending":0,"browsers":{"chrome":{"90.0":{},"91.0":{}},"firefox":{"88.0":{},"89.0":{}},"opera":{"76.0":{},"77.0":{}}}}

total: 20
total: {90.0={}, 91.0={}}
 */
    }

    @Test
    void checkTotal20WithAssertJ() {
        Integer response = get("https://selenoid.autotests.cloud/status")
                .then()
                .extract().path("total");

        assertThat(response).isEqualTo(20);
    }

    @Test
    void checkStatus200() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200);
    }

    @Test
    void checkStatus401() {
        get("https://selenoid.autotests.cloud/wd/hub/status")
                .then()
                .statusCode(401);
    }

    @Test
    void checkWdHubSValueReady() {
        get("https://user1:1234@selenoid.autotests.cloud/wd/hub/status")
                .then()
                .statusCode(200)
                .body("value.ready", is(true));
        //{"value":{"message":"Selenoid 1.10.3 built at 2021-03-28_06:50:36AM","ready":true}}
    }

    @Test
    void checkWdHubBasicAuth() {
        given().auth().basic("user1", "1234")
                .get("https://selenoid.autotests.cloud/wd/hub/status")
                .then()
                .statusCode(200)
                .body("value.ready", is(true));
        //{"value":{"message":"Selenoid 1.10.3 built at 2021-03-28_06:50:36AM","ready":true}}
    }
}
