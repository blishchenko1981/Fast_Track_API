package com.ally.day01;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class p01_SimpleGETRequest {


    @Test
    public void simpleTest() {

        Response response = RestAssured.get("http://3.91.96.199:1000/ords/hr/regions");
        // Response
        // print response and keep method chaining
        response.prettyPeek();
        // it prints response only
        // response.prettyPrint();
        System.out.println("------------------------------------------------------------");

        // Headers
        System.out.println(response.getHeaders());
        System.out.println(response.headers());
        System.out.println("-------------------------------------------------------------");

        // Content-Type
        System.out.println(response.getContentType());
        System.out.println(response.contentType());
        System.out.println("-------------------------------------------------------------");

        // Status code
        System.out.println(response.statusCode());
        System.out.println(response.getStatusCode());
        System.out.println("--------------------------------------------------------------");

        // Date(try to check "Date" header is exist)
        System.out.println("response.header(\"Date\") = " + response.header("Date"));

        // Verify response has Date
        System.out.println("response.headers().hasHeaderWithName(\"Date\") = " + response.headers().hasHeaderWithName("Date"));
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));
        System.out.println("--------------------------------------------------------------");


        // Verify response body has "Europe"
        System.out.println("response.asString().contains(\"Europe\") = " + response.asString().contains("Europe"));
        Assertions.assertTrue(response.asString().contains("Europe"));

    }

    @DisplayName("GET/employees/100")
    @Test
    public void getOneEmployee(){

      Response response =   RestAssured.get("http://3.91.96.199:1000/ords/hr/employees/100").prettyPeek();

        System.out.println("-----------------------------------------------------------------------");

      //First Name
       String firstName =  response.path("first_name");
        System.out.println("firstName = " + firstName);

        System.out.println("-----------------------------------------------------------------");

        // Last Name
        String lastName = response.path("last-name");
        System.out.println("lastName = " + lastName);
        System.out.println("-----------------------------------------------------------------");

        // Status code is 200
        Assertions.assertEquals(200, response.getStatusCode());
        System.out.println("Or we can use HttpStatus.SC_OK");
        Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode());
        System.out.println("-----------------------------------------------------------------");

        //Verify first name is Steven

        Assertions.assertEquals("Steven", firstName);
        System.out.println("-----------------------------------------------------------------");


        //Verify content-Type is application/json
        System.out.println("response.contentType() = " + response.contentType());
        Assertions.assertEquals("application/json", response.contentType());
        Assertions.assertEquals(ContentType.JSON.toString(), response.contentType());
    }


}
