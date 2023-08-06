package day1;

/*
    given()
        // content type, set cookies, add auth, add params, set headers info etc

    when()
        // all the request type GET, POST etc., all the request urls

    then()
        // all validations here, validate status code, extract headers cookies and response body
 */

/*

GET USER: https://reqres.in/api/users/2

POST USER: https://reqres.in/api/users
            BODY:{
                  "name": "morpheus",
                  "job": "leader"
                  }

UPDATE USER: https://reqres.in/api/users/2
            BODY:{
                  "name": "morpheus",
                  "job": "zion resident"
                  }
 */

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.TestRunner.PriorityWeight.priority;


public class HTTPRequests {
    int id;
    @Test
    void getAllUsers(){
        given()

                .when()
                    .get("https://reqres.in/api/users?page=2")
                .then()
                    .statusCode(200)
                    .body("page", equalTo(2))
                    .log().all();
    }

    @Test
    void createNewUser(){
        Map<String, String> map=new HashMap<>();
        map.put("name","Morpheus");
        map.put("job","leader");

        given()
                .contentType("application/json")
                .body(map)

                .when()
                    .post("https://reqres.in/api/users")

                .then()
                    .statusCode(201)
                    .log().all();
    }

}
