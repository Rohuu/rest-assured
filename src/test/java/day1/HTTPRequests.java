package day1;

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class HTTPRequests {
    int id;
    @Test(priority = 1)
    void shouldBeAbleToGetAllUsers(){
        given()

                .when()
                    .get("https://reqres.in/api/users?page=2")
                .then()
                    .statusCode(200)
                    .body("page", equalTo(2))
                    .log().all();
    }

    @Test(priority = 2)
    void shouldBeAbleToCreateNewUser(){
        Map<String, String> map=new HashMap<>();
        map.put("name","Morpheus");
        map.put("job","leader");

         id= given()
                .contentType("application/json")
                .body(map)

                .when()
                    .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");

//                .then()
//                    .statusCode(201)
//                    .log().all();
    }

    @Test(priority = 3, dependsOnMethods = ("shouldBeAbleToCreateNewUser"))
    void shouldBeAbleToUpdateTheUser(){
        Map<String, String> updatedData=new HashMap<>();
        updatedData.put("name","RohitS");
        updatedData.put("job","plumber");

        given()
                .contentType("application/json")
                .body(updatedData)

                .when()
                        .put("https://reqres.in/api/users/"+id)

                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 4)
    void shouldBeAbleToDeleteUser(){
        given()

                .when()
                .delete("https://reqres.in/api/users/"+id)

                .then()
                .statusCode(204)
                .log().all();
    }
}





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

