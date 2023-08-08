package day2;


import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

/*
Different ways to create POST request body
1. using hashmap
2. using Org.JSON
3. using POJO class
4. using external JSON
 */
public class WaysToCreatePostRequestBody {

    // 1. Using hashmap
    // this is preferred if we have small set of data

    @Test(priority = 1)
    void shouldBeAbleToCreatePostRequestBodyUsingHashmap(){
        Map<String,String> data= new HashMap<>();
        data.put("name","KaluS");
        data.put("location","Haldwani");
        data.put("phone","7409904341");
        String[] courses={"c","c++","java","spring-boot"};
        data.put("courses", Arrays.toString(courses));

        given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name",equalTo("KaluS"))
                .body("location",equalTo("Haldwani"))
                .body("phone",equalTo("7409904341"))
//                .body("courses[0]",equalTo("c"))
//                .body("courses[1]",equalTo("c++"))
//                .body("courses[2]",equalTo("java"))
//                .body("courses[3]",equalTo("spring-boot"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();
        // validated values and all parameters of header
        // for header values to be compared, no need to write equal to
    }


    // POST request body using org.json library
    @Test(priority = 3)
    void shouldBeAbleToCreatePostRequestBodyUsingJsonLibrary(){
        JSONObject data=new JSONObject();
        data.put("name","SagarK");
        data.put("location","Block");
        data.put("phone","9760090464");
        String[] courses={"c","c++","java"};
        data.put("courses",courses);

        given()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name",equalTo("SagarK"))
                .body("location",equalTo("Block"))
                .body("courses[0]",equalTo("c"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();
    }

    // POST request body using POJO class
    @Test
    void shouldBeAbleToCreatePostRequestBodyUsingPojoClass(){
        Person personData = new Person();
        personData.setName("Kamal");
        personData.setLocation("Rudrapur");
        personData.setPhone("1234567890");
        personData.setCourses(new String[]{"compiler design","DAA","OOP"});

        given()
                .contentType("application/json")
                .body(personData)

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .header("Content-Type","application/json; charset=utf-8")
                .body("name",equalTo("Kamal"))
                .body("location",equalTo("Rudrapur"))
                .body("courses[0]",equalTo("compiler design"))
                .body("courses[1]",equalTo("DAA"))
                .log().all();
    }
}
