package api_tests;

import base.BaseApiTest;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.AllureUtils;

public class ApiTests extends BaseApiTest {

    @Test
    public void API01_getAllProductsList() {
        Response r = req().filter(new AllureRestAssured()).get("/productsList");
        AllureUtils.attachRequestResponse("GET /productsList", r.asPrettyString());
        Assert.assertEquals(r.statusCode(), 200);
    }

    @Test
    public void API02_getAllBrandsList() {
        Response r = req().filter(new AllureRestAssured()).get("/brandsList");
        AllureUtils.attachRequestResponse("GET /brandsList", r.asPrettyString());
        Assert.assertEquals(r.statusCode(), 200);
    }

    @Test
    public void API03_searchProduct() {
        Response r = req().filter(new AllureRestAssured())
                .formParam("search_product", "top")
                .post("/searchProduct");
        AllureUtils.attachRequestResponse("POST /searchProduct (search_product=top)", r.asPrettyString());
        Assert.assertEquals(r.statusCode(), 200);
    }

    @Test
    public void API04_verifyLoginValid() {
        Response r = req().filter(new AllureRestAssured())
                .formParam("email", "test@test.com")
                .formParam("password", "12345")
                .post("/verifyLogin");
        AllureUtils.attachRequestResponse("POST /verifyLogin", r.asPrettyString());
        Assert.assertEquals(r.statusCode(), 200);
    }

    @Test
    public void API05_verifyLoginWithoutEmail() {
        Response r = req().filter(new AllureRestAssured())
                .formParam("password", "12345")
                .post("/verifyLogin");
        AllureUtils.attachRequestResponse("POST /verifyLogin (no email)", r.asPrettyString());
        Assert.assertEquals(r.statusCode(), 200);
    }

    @Test
    public void API06_verifyLoginNoParams() {
        Response r = req().filter(new AllureRestAssured()).post("/verifyLogin");
        AllureUtils.attachRequestResponse("POST /verifyLogin (no params)", r.asPrettyString());
        Assert.assertEquals(r.statusCode(), 200);
    }

    @Test
    public void API07_createAccount() {
        String email = "user" + System.currentTimeMillis() + "@mail.com";
        Response r = req().filter(new AllureRestAssured())
                .formParam("name", "User")
                .formParam("email", email)
                .formParam("password", "12345")
                .formParam("title", "Mr")
                .formParam("birth_date", "1")
                .formParam("birth_month", "1")
                .formParam("birth_year", "2000")
                .formParam("firstname", "User")
                .formParam("lastname", "Test")
                .formParam("company", "Company")
                .formParam("address1", "Address")
                .formParam("address2", "Address2")
                .formParam("country", "Georgia")
                .formParam("zipcode", "12345")
                .formParam("state", "State")
                .formParam("city", "City")
                .formParam("mobile_number", "1234567890")
                .post("/createAccount");
        AllureUtils.attachRequestResponse("POST /createAccount (email=" + email + ")", r.asPrettyString());
        Assert.assertEquals(r.statusCode(), 200);
    }

    @Test
    public void API08_getUserDetailByEmail() {
        Response r = req().filter(new AllureRestAssured())
                .queryParam("email", "test@test.com")
                .get("/getUserDetailByEmail");
        AllureUtils.attachRequestResponse("GET /getUserDetailByEmail", r.asPrettyString());
        Assert.assertEquals(r.statusCode(), 200);
    }

    @Test
    public void API09_updateAccount() {
        Response r = req().filter(new AllureRestAssured())
                .formParam("name", "Updated")
                .formParam("email", "test@test.com")
                .formParam("password", "12345")
                .formParam("firstname", "Updated")
                .formParam("lastname", "User")
                .formParam("address1", "New Address")
                .formParam("country", "Georgia")
                .formParam("zipcode", "99999")
                .formParam("state", "State")
                .formParam("city", "City")
                .formParam("mobile_number", "1234567890")
                .put("/updateAccount");
        AllureUtils.attachRequestResponse("PUT /updateAccount", r.asPrettyString());
        Assert.assertEquals(r.statusCode(), 200);
    }

    @Test
    public void API10_deleteAccount() {
        Response r = req().filter(new AllureRestAssured())
                .formParam("email", "test@test.com")
                .formParam("password", "12345")
                .delete("/deleteAccount");
        AllureUtils.attachRequestResponse("DELETE /deleteAccount", r.asPrettyString());
        Assert.assertEquals(r.statusCode(), 200);
    }
}
