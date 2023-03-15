package com.ally.day03;

import com.ally.POJO.Campus;
import com.ally.POJO.Cluster;
import com.ally.POJO.Room;
import com.ally.utility.BookitTestBase;
import com.ally.utility.BookitUtils;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class P04_bookitApi_bearerToken extends BookitTestBase {

    @Test

    public void getCampuses() {

        String email = "blyst6@si.edu";
        String password = "barbabaslyst";

        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", email);
        credentials.put("password", password);

        // GET TOKEN
        String token = BookitUtils.getToken(email, password);

        // GET CAMPUSES

        JsonPath jp = given().accept(ContentType.JSON)
                .header("Authorization", token).
                when().get("/api/campuses").
                then().statusCode(200)
                .contentType(ContentType.JSON)
                .extract().jsonPath();

        // De-serialization
        List<Campus> listOfCampuses = jp.getList("", Campus.class);
        //     System.out.println("listOfCampuses = " + listOfCampuses);

        System.out.println("listOfCampuses = " + listOfCampuses);

        // List of clusters
       List<Cluster> listOfClusters =  jp.getList("clusters");
        System.out.println("listOfClusters = " + listOfClusters);
        System.out.println("listOfClusters.get(0) = " + listOfClusters.get(0));

        //how many campuses
        System.out.println("listOfCampuses.size() = " + listOfCampuses.size());

        // how many clusters in VA location
        System.out.println("number clusters in VA = " + listOfCampuses.get(0).getClusterList().size());

        // how many rooms  light side
        System.out.println("rooms in light side = " + listOfCampuses.get(0).getClusterList().get(0).getRoomList().size());


        // all rooms with capacity is 6
        //  example:    jp.getList("items.findAll{it.country_id=='UK'}.city");
        List<Room> rooms = listOfCampuses.get(0).getClusterList().get(0).getRoomList();


        System.out.println("rooms = " + rooms);

        List<Room> roomsss = jp.getList("[0].clusters[0].rooms", Room.class);

        System.out.println("roomsss = " + roomsss);

//        for (Room room : rooms) {
//            System.out.println("room = " + room);
//
//            if(room.getCapacity() == 6){
//                System.out.println("room.getName() = " + room.getName());
//            }
//        }

        System.out.println("roomsss.get(0).getCapacity() = " + roomsss.get(0).getCapacity());


        for (Room room : roomsss) {
            if (room.getCapacity() == 6) {
                System.out.println("room = " + room.getName());
            }


        }


    }


    @Test
    public void testRoom(){
        String email = "blyst6@si.edu";
        String password = "barbabaslyst";

        // GET TOKEN
        String token = BookitUtils.getToken(email, password);

        // GET CAMPUSES

        JsonPath jasonPath = given().accept(ContentType.JSON)
                .header("Authorization", token).
                when().get("/api/campuses").
                then().statusCode(200)
                .contentType(ContentType.JSON)
                .extract().jsonPath();

       List<Campus> campuses =  jasonPath.getList("", Campus.class);
        System.out.println("campuses = " + campuses);

        // get first name of class

        String name = jasonPath.getString("[0].clusters[0].rooms.name[0]");
   //     System.out.println("name = " + name);

        //  //  example:    jp.getList("items.findAll{it.country_id=='UK'}.city");

        List<Room> roomsWith6 = jasonPath.getList("[0].clusters[0].rooms.findAll{it.capacity==6}", Room.class);
        System.out.println("roomsWith6 = " + roomsWith6);


    }
}


