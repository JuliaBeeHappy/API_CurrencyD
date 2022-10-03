import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class NegativeTests {
    private static Response response;

    @Test
    public void invalidKeyNegativeTest() {
        response =  given().get(Const.URL + Const.INVALID_Live_ENDPOINT);
        System.out.println(response.asString());
        response.then().statusCode(401);
        response.then().body("message", equalTo("Invalid authentication credentials"));
    }

    @Test
    public void noKeyTestNegativeTest() {
        response =  given().get(Const.URL);
        System.out.println(response.asString());
        response.then().statusCode(404);
        response.then().body("message", containsString("no Route matched"));

    }
    @Test
    public void incorrectCurrenciesNegativeTest() {
        response = given().get(Const.URL + Const.INCORRECT_CURRENCIES);
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("error.code", is(202));

    }
    @ParameterizedTest
    @ValueSource(strings = {"EURW", "CAD", "RUB", "ILS" })
    public void listOfDefinedCurrenciesNegativeTest(String Currencies){
        response = given().get(Const.URL + Const.Live_ENDPOINT + "&currencies=" + Currencies);
        System.out.println(response.asString());
        response.then().statusCode(200);
        //  response.then().body("success", is(true));
        response.then().body("error.code", is(202));
    }
    @ParameterizedTest
    @ValueSource(strings = {"2020-15-60"})
    public void invalidDateNegativeTest(String Dates) {
        response = given().get(Const.URL + Const.Historical_ENDPOINT_CAD + "&DATE=" + Dates);
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("success", is(false));
        response.then().body("error.code", is(302));
        response.then().body("error.info", containsString("You have entered an invalid date."));
    }


    @Test
    public void missingDateNegativeTest() {
        response = given().get(Const.URL + Const.Historical_ENDPOINT_CAD);
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("success", is(false));
        response.then().body("error.code", is(301));
        response.then().body("error.info", containsString("You have not specified a date."));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1982-01-01"})
    public void currenciesNotExitTest(String Dates) {
        response = given().get(Const.URL + Const.Historical_ENDPOINT_EUR + "&DATE=" + Dates);
        System.out.println(response.asString());
       response.then().statusCode(404);
      response.then().body("message", containsString("no Route matched with those values"));
    }
}
