import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class LiveEndpointTest {
    private static Response response;


    @Test
    public void eurCurrencies() {
        response = given().get(Const.URL + Const.CURRENCIES_EUR);
        System.out.println(response.asString());
        response.then().statusCode(200);
    }
    @Test
    public void cadCurrencies() {
        response = given().get(Const.URL + Const.CURRENCIES_CAD);
        System.out.println(response.asString());
        response.then().statusCode(200);
    }
    @Test
    public void ilsCurrencies() {
        response = given().get(Const.URL + Const.CURRENCIES_ILS);
        System.out.println(response.asString());
        response.then().statusCode(200);
    }
    @Test
    public void rubCurrencies() {
        response = given().get(Const.URL + Const.CURRENCIES_RUB);
        System.out.println(response.asString());
        response.then().statusCode(200);
    }

    @ParameterizedTest
    @ValueSource(strings = {"EUR", "CAD", "RUB", "ILS" })
    public void listOfDefinedCurrencies(String Currencies){
        response = given().get(Const.URL + Const.Live_ENDPOINT + "&currencies=" + Currencies);
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("success", is(true));
    }

}