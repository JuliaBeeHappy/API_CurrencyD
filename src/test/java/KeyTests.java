import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class KeyTests {

    private static Response response;


    @Test
    public void validKeyTest(){
        response =  given().get(Const.URL + Const.Live_ENDPOINT);
        response.then().statusCode(200);
    }

}


