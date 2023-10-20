package api.apiTest.filaC;

import api.factoryRequest.FactoryRequest;
import api.factoryRequest.RequestInfo;
import api.utils.Properties;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class Ejercicio2Test {
    RequestInfo requestInfo = new RequestInfo();
    Response response;
    JSONObject body = new JSONObject();
    String auth;

    @BeforeEach
    public void setup() {
        auth = Base64.getEncoder().encodeToString((Properties.userRand + ":" + Properties.pwd).getBytes());
    }

    @Test
    public void verifyEjercicio1() {
        List<Integer> userIds = new ArrayList<>();
        List<JSONObject> users = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            JSONObject newUser = new JSONObject();
        }

    }

    private void createUser(JSONObject newUser){
        body.clear();
        body.put("Email", newUser.toString());
        body.put("Password", Properties.pwd);
        body.put("FullName", newUser.toString());
        requestInfo.setHost(Properties.host + "api/user.json").setBody(body.toString());
        response = FactoryRequest.make("post").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("Email", equalTo(body.get("Email")))
                .body("FullName", equalTo(body.get("FullName")));

    }

    private void deleteUser(JSONObject userBody){
        requestInfo.setHost(Properties.host +"api/user/0.json").setHeader("Authorization", "Basic " + auth);
        response = FactoryRequest.make("delete").send(requestInfo);

        response.then().log().all().statusCode(200)
                .body("Email", equalTo(userBody.get("Email")))
                .body("FullName", equalTo(userBody.get("FullName")));
    }

}