//package tz_7.GamePlay.GameStateDatabase;
//
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.junit.Before;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.test.context.junit4.SpringRunner;
//import tz_7.GamePlay.GameLobbyDatabase.GameLobby;
//import tz_7.PlayerDatabase.Player;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@RunWith(SpringRunner.class)
//public class TestingGameStateController {
//
//    @LocalServerPort
//    int port;
//
//    @Before
//    public void setUp() {
//        RestAssured.port = port;
//        RestAssured.baseURI = "http://localhost";
//    }
//
//    @Test
//    public void b_createLobbyTest() throws JSONException {
//        Player player = new Player("test", "test", "test@test.test", "test", "test", "b");
//        GameLobby lobby = new GameLobby(2, "test", player, false);
//
//        Response response = RestAssured.given().
//                header("Content-Type", "application/json").
//                header("charset","utf-8").
//                body(lobby).
//                when().
//                post("/lobby/new/{hostid}", 1);
//        String s = response.getBody().asString();
//        JSONObject obj = new JSONObject(s);
//
//        response = RestAssured.given().
//                header("Content-Type", "application/json").
//                header("charset", "utf-8").
//                when().
//                post("/game/new/lobby/{id}", obj.get("id"));
//
//        int statusCode = response.getStatusCode();
//        assertEquals(200, statusCode);
//
//        String tmp = response.getBody().asString();
//        try {
//            JSONObject object = new JSONObject(tmp);
//            assertEquals(1, object.get("hostID"));
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
////    @Test
////    public void z_deleteGame() throws JSONException {
////        Response response = RestAssured.given().
////                header("charset","utf-8").
////                when().
////                get("/game/hosts/{id}", 1);
////        String s = response.getBody().asString();
////        JSONObject obj = new JSONObject(s);
////
////        response = RestAssured.given().
////                header("charset","utf-8").
////                when().
////                delete("/game/{id}/delete", obj.get("id"));
////
////        int statusCode = response.getStatusCode();
////        assertEquals(200, statusCode);
////    }
//}
