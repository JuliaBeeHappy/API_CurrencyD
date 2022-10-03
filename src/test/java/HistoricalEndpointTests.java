import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HistoricalEndpointTests {
    private static Response response;

    @Test
    public void historicalAllCurrencies() {
        response = given().get(Const.URL + Const.Historical_ENDPOINT);
        System.out.println(response.asString());
        response.then().statusCode(200);

    }

    @ParameterizedTest
    @ValueSource(strings = {"EUR", "CAD", "RUB", "ILS"})
    public void listOfDefinedCurrencies(String Currencies) {
        response = given().get(Const.URL + Const.Historical_ENDPOINT + "&currencies=" + Currencies);
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("success", is(true));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2020-01-01", "2018-02-03"})
    public void usToCadCorrectDate(String Dates) {
        response = given().get(Const.URL + Const.Historical_ENDPOINT_CAD + "&DATE=" + Dates);
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("success", is(true));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2020-01-01"})
    public void usToRubCorrectDate(String Dates) {
        response = given().get(Const.URL + Const.Historical_ENDPOINT_RUB + "&DATE=" + Dates);
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("success", is(true));
        response.then().body("quotes.USDRUB", equalTo(61.865021f));
    }

    @Test
    public void multipleCurrenciesCorrectDate() {
        response = given().get(Const.URL + Const.Historical_ENDPOINT_MULTIPLE);
        System.out.println(response.asString());
        response.then().statusCode(200);

    }


}

