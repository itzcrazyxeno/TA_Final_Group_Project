package base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

public class BaseApiTest {

    @BeforeMethod
    public void apiSetup() {
        RestAssured.baseURI = ConfigReader.get("api.baseUrl");
    }

    protected RequestSpecification req() {
        return RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8");
    }
}
