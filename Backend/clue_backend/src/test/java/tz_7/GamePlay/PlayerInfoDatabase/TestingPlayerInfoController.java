package tz_7.GamePlay.PlayerInfoDatabase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
import tz_7.PlayerDatabase.Player;

import javax.validation.constraints.NotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
public class TestingPlayerInfoController {

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void b_createInfo() {
        PlayerInfo info = new PlayerInfo(false);
        Response response = RestAssured.given().
                header("Content-Type", "application/json").
                header("charset","utf-8").
                body(info).
                when().
                post("/info/{id}", 1);

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String returnLobby = response.getBody().asString();
        try {
            JSONObject object = new JSONObject(returnLobby);
            assertEquals(false, object.get("turn"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void d_chooseRoleTest() {
        int id;
        Response response = RestAssured.given().
                header("charset","utf-8").
                when().
                get("/info/player/{id}", 1);

        String s = response.getBody().asString();
        try {
            JSONObject obj = new JSONObject(s);
            id = (int) obj.get("id");
        response = RestAssured.given().
                header("charset","utf-8").
                when().
                put("info/{id}/role/{charid}", id, "green");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        response = RestAssured.given().
                header("charset","utf-8").
                when().
                get("/info/player/{id}", 1);
        s = response.getBody().asString();
        try {
            JSONObject obj = new JSONObject(s);
            JSONObject role = (JSONObject) obj.get("role");
            assertEquals("green", role.get("name"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void z_deleteInfoTest() {
        int id;
        Response response = RestAssured.given().
                header("charset","utf-8").
                when().
                get("/info/player/{id}", 1);

        String s = response.getBody().asString();
        try {
            JSONObject obj = new JSONObject(s);
            id = (int) obj.get("id");
            response = RestAssured.given().
                    header("charset","utf-8").
                    when().
                    delete("info/{id}/delete", id);

            int statusCode = response.getStatusCode();
            assertEquals(200, statusCode);

            response = RestAssured.given().
                    header("charset","utf-8").
                    when().
                    delete("/info/player/{id}", 1);
            statusCode = response.getStatusCode();
            assertEquals(405, statusCode);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


    }
}
