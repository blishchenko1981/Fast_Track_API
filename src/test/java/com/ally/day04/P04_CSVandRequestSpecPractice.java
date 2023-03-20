package com.ally.day04;

import com.ally.utility.SpartanAuthTestBase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;

public class P04_CSVandRequestSpecPractice extends SpartanAuthTestBase {


    @ParameterizedTest
    @CsvFileSource(resources = "/spartanRoles.csv", numLinesToSkip = 1)
    public void testParmeterized(String username, String password, int id, int statusCode) {
        given().spec(reqSpec(username, password)).log().all()
                .pathParam("id", id).
                when().get("/spartans/{id}").
                then().spec(respSpec(statusCode)).log().all();
    }
}
