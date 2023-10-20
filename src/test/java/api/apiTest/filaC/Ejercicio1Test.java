package api.apiTest.filaC;

import api.factoryRequest.FactoryRequest;
import api.factoryRequest.RequestInfo;
import api.utils.Properties;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.hamcrest.Matchers.equalTo;

public class Ejercicio1Test {
    RequestInfo requestInfo = new RequestInfo();
    Response response;
    JSONObject body = new JSONObject();
    String auth;

    @BeforeEach
    public void setup() {
        auth = Base64.getEncoder().encodeToString((Properties.userRand+":"+Properties.pwd).getBytes());
    }

    @Test
    public void verifyEjercicio1() {
        //Create User
        body.clear();
        body.put("Email", Properties.userRand);
        body.put("Password", Properties.pwd);
        body.put("FullName", Properties.userRand);
        requestInfo.setHost(Properties.host+"api/user.json").setBody(body.toString());
        response = FactoryRequest.make("post").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("Email", equalTo(body.get("Email")))
                .body("FullName", equalTo(body.get("FullName")));

        //Token
        body.clear();
        requestInfo.setHost(Properties.host+"api/authentication/token.json").setHeader("Authorization","Basic "+auth);
        response = FactoryRequest.make("get").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("UserEmail", equalTo(Properties.userRand));

        String token = response.then().extract().path("TokenString");


        //Create Item
        body.put("Content", "UPB ITEM QA TESTING");
        requestInfo.removeHeader("Authorization").setHost(Properties.host+"api/items.json").setBody(body.toString()).setHeader("Token",token);
        response = FactoryRequest.make("post").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("Content", equalTo(body.get("Content")));


        //Delete Token
        requestInfo.setHost(Properties.host+"api/authentication/token.json").setHeader("Token",token);
        response = FactoryRequest.make("delete").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("TokenString", equalTo(token));


        //Create Item with deleted token
        body.clear();
        body.put("Content", "UPB QA TESTING");
        body.put("Icon", 5);
        requestInfo.setHost(Properties.host+"api/items.json").setBody(body.toString()).setHeader("Token",token);
        response = FactoryRequest.make("post").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("ErrorCode", equalTo(102));
    }
}
