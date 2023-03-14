package com.ally.day02;

import com.ally.utility.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_JsonPath extends HrTestBase {

    @Test
    public void getLocations(){

        Response response = given().accept(ContentType.JSON).
                when().get("/locations");

        assertEquals(200, response.statusCode());

        assertEquals(ContentType.JSON.toString(), response.contentType());

        JsonPath jp = response.jsonPath();

        System.out.println("jp.getString(\"items[1].city\") = " + jp.getString("items[1].city"));

        System.out.println("jp.getString(\"items[-1].city\") = " + jp.getString("items[-1].city"));

       List<String> allCountryIDs =  jp.getList("items.country_id");
        System.out.println("allCountryIDs = " + allCountryIDs);

        // get all city where their country id is UK
        List<String> allUKcities = jp.getList("items.findAll{it.country_id=='UK'}.city");
        System.out.println("allUKcities = " + allUKcities);
    }
}
