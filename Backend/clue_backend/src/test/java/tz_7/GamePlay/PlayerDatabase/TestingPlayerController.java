package tz_7.GamePlay.PlayerDatabase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import tz_7.GamePlay.GameLobbyDatabase.GameLobby;
import tz_7.GamePlay.PlayerInfoDatabase.PlayerInfo;
import tz_7.PlayerDatabase.Player;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
public class TestingPlayerController {

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void a_runTest() {
        // Send request and receive response
        PlayerInfo info = new PlayerInfo(false);
        Response response = RestAssured.given().
                header("charset","utf-8").
                when().
                get("/allUsers");

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String users = response.getBody().asString();
        try {
            JSONArray object = new JSONArray(users);
            assertTrue(0 < object.length());
        } catch (JSONException e) {
            throw new RuntimeException();
        }
    }

    @Test
    public void b_createPlayerTest() {
        Player player = new Player("test", "test", "test@test.test", "test", "test", "b");
        Response response = RestAssured.given().
                header("Content-Type", "application/json").
                header("charset","utf-8").
                body(player).
                when().
                post("/register");

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String returnPlayer = response.getBody().asString();
        try {
            JSONObject object = new JSONObject(returnPlayer);
            String username = object.get("username").toString();
            assertEquals("test", username);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void c_changePassword() {
        Response response = RestAssured.given().
                header("charset","utf-8").
                when().
                put("/changePassword/{id}/to/{password}", 1, "test1");

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String returnPlayer = response.getBody().asString();
        try {
            JSONObject object = new JSONObject(returnPlayer);
            String password = object.get("password").toString();
            assertEquals("test1", password);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void d_changeUsername() {
        Response response = RestAssured.given().
                header("charset","utf-8").
                when().
                put("/changeUsername/{id}/to/{password}", 1, "test1");

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String returnPlayer = response.getBody().asString();
        try {
            JSONObject object = new JSONObject(returnPlayer);
            String username = object.get("username").toString();
            assertEquals("test1", username);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void e_updateType() {
        Response response = RestAssured.given().
                header("charset","utf-8").
                when().
                put("/updateType/{id}", 1);

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String returnPlayer = response.getBody().asString();
        try {
            JSONObject object = new JSONObject(returnPlayer);
            String type = object.get("type").toString();
            assertEquals("p", type);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

//    @Test
//    public void f_updateType_returnType() {
//        Response response = RestAssured.given().
//                header("charset","utf-8").
//                when().
//                put("/updateType/{id}", 1);
//
//        int statusCode = response.getStatusCode();
//        assertEquals(200, statusCode);
//
//        String returnPlayer = response.getBody().asString();
//        try {
//            JSONObject object = new JSONObject(returnPlayer);
//            Response response1 = RestAssured.given().
//                    header("charset","utf-8").
//                    when().
//                    put("/getUserType/{id}", 1);
//            int statusCode1 = response.getStatusCode();
//            assertEquals(200, statusCode1);
//
//            String type = response1.getBody().asString();
//            assertEquals("p", type);
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

    @Test
    public void f_getUserByIdTest() {
        int id;
        Response response = RestAssured.given().
                header("charset","utf-8").
                when().
                get("/getUser/{id}", 1);

        String s = response.getBody().asString();
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String returnPlayer = response.getBody().asString();
        try {
            JSONObject object = new JSONObject(returnPlayer);
            String username = object.get("username").toString();
            assertEquals("test", username);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
//    @Test
//    public void z_deletePlayerTest() {
//        int id;
//        Response response = RestAssured.given().
//                header("charset","utf-8").
//                when().
//                get("/getUser/{id}", 1);
//
//        String s = response.getBody().asString();
//        try {
//            JSONObject obj = new JSONObject(s);
//            id = (int) obj.get("id");
//            response = RestAssured.given().
//                    header("charset","utf-8").
//                    when().
//                    delete("/deleteUser/{id}", id);
//
//            int statusCode = response.getStatusCode();
//            assertEquals(200, statusCode);
//
//            response = RestAssured.given().
//                    header("charset","utf-8").
//                    when().
//                    get("/getUser/{id}", id);
//            statusCode = response.getStatusCode();
//            assertEquals(405, statusCode);
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
}

