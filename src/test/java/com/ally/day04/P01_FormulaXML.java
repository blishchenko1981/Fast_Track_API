package com.ally.day04;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;


public class P01_FormulaXML {

    //  http://ergast.com/api/f1/drivers
    @Test
    public void xmlTest(){

      Response response =  get("http://ergast.com/api/f1/drivers")
              .then()
              .statusCode(200).extract().response();

      XmlPath xmlPath = response.xmlPath();

      // first driver Given_Name

        String firstDriver = xmlPath.getString("MRData.DriverTable.Driver[0].GivenName");
        System.out.println("firstDriver = " + firstDriver);

      List<String> allNames =   xmlPath.getList("MRData.DriverTable.Driver.GivenName");
        System.out.println("allNames = " + allNames);
        System.out.println("allNames.size() = " + allNames.size());

       String driverId = xmlPath.getString( "MRData.DriverTable.Driver[0].@driverId" );
        System.out.println("driverId = " + driverId);


       List<String> allId =  xmlPath.getList( "MRData.DriverTable.Driver.@driverId" );
        System.out.println("allId = " + allId);


        // Print out all driver given name if their nationality is Italian

        List<String> allItalianDrivers = xmlPath.getList("MRData.DriverTable.Driver.findAll{it.Nationality == 'Italian'}.GivenName");

        System.out.println("allItalianDrivers = " + allItalianDrivers);

        System.out.println("response.getTime() = " + response.getTime());
    }
}
