package com.ally.day02;

import com.ally.utility.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_ResponsePath extends HrTestBase {

    @Test
    public void getSingleRegion() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 2).
                when().get("regions/{id}");

        assertEquals(200, response.statusCode());

        assertEquals("Americas", response.path("region_name"));

        assertEquals(2, (Integer) response.path("region_id"));


        List<Map<String, String>> linksList = response.path("links");


        System.out.println("----How to print each link info-------------------");

        for (Map<String, String> map : linksList) {

            System.out.println("map.get(\"rel\") = " + map.get("rel"));
            System.out.println("map.get(\"href\") = " + map.get("href"));

            System.out.println("------------------------------------------------");

        }

        // get whole href info

        List<String> listHref = response.path("links.href");

        System.out.println("listHref = " + listHref);


    }

    @ParameterizedTest
    @CsvFileSource(resources = "/regions.csv", numLinesToSkip = 1)

    public void parameterizedTest(int id, String regionName){

        System.out.println("id = " + id);
        System.out.println("regionName = " + regionName);

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", id).
                when().get("regions/{id}");

        assertEquals(200, response.statusCode());

        assertEquals(regionName, response.path("region_name"));

        assertEquals(id, (Integer) response.path("region_id"));
    }
}
