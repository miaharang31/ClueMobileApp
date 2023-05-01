package tz_7.GamePlay.GameLobbyDatabase;

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


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
public class TestingGameLobbyController {

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
		Response response = RestAssured.given().
				header("Content-Type", "text/plain").
				header("charset","utf-8").
				when().
				get("/lobby");

		// Check status code
		int statusCode = response.getStatusCode();
		assertEquals(200, statusCode);
	}

	@Test
	public void b_createLobbyTest() {
		Player player = new Player("test", "test", "test@test.test", "test", "test", "b");
		GameLobby lobby = new GameLobby(2, "test", player, false);
		Response response = RestAssured.given().
				header("Content-Type", "application/json").
				header("charset","utf-8").
				body(lobby).
				when().
				post("/lobby/new/{hostid}", 1);

		int statusCode = response.getStatusCode();
		assertEquals(200, statusCode);

		String returnLobby = response.getBody().asString();
		try {
			JSONObject object = new JSONObject(returnLobby);
			JSONArray arr = (JSONArray) object.get("players");
			assertEquals(0, arr.length());
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void d_addPlayersTest() {
		Response response = RestAssured.given().
				header("charset","utf-8").
				when().
				put("lobby/join/{playerID}/code/{gamecode}", 5, "test");

		int statusCode = response.getStatusCode();
		assertEquals(200, statusCode);

		String returnLobby = response.getBody().asString();
		try {
			JSONObject object = new JSONObject(returnLobby);
			JSONArray arr = (JSONArray) object.get("players");
			assertEquals(1, arr.length());
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}
@Test
	public void e_addPlayerMaxTest() {
		Response response = RestAssured.given().
				header("charset","utf-8").
				when().
				put("lobby/join/{playerID}/code/{gamecode}", 4, "test");

		int statusCode = response.getStatusCode();
		assertEquals(200, statusCode);

		String returnLobby = response.getBody().asString();
		try {
			JSONObject object = new JSONObject(returnLobby);
			JSONArray arr = (JSONArray) object.get("players");
			assertEquals(1, arr.length());
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void e_getHostTest() {
		int id;
		Response response = RestAssured.given().
				header("charset","utf-8").
				when().
				get("/lobby/find/host/{id}", 1);

		String s = response.getBody().asString();
		try {
			JSONObject obj = new JSONObject(s);
			id = (int) obj.get("id");
			response = RestAssured.given().
					header("charset", "utf-8").
					when().
					get("lobby/host/{gameLobbyID}", id);
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}

		s = response.getBody().asString();
		try {
			JSONObject object = new JSONObject(s);
			int playerid = (int) object.get("id");
			assertEquals(1, playerid);
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void z_deleteLobbyTest() {
		int id;
		Response response = RestAssured.given().
				header("charset","utf-8").
				when().
				get("/lobby/find/host/{id}", 1);

		String s = response.getBody().asString();
		try {
			JSONObject obj = new JSONObject(s);
			id = (int) obj.get("id");
			response = RestAssured.given().
					header("charset","utf-8").
					when().
					delete("/lobby/delete/{id}", id);

			int statusCode = response.getStatusCode();
			assertEquals(200, statusCode);

			response = RestAssured.given().
					header("charset","utf-8").
					when().
					delete("/lobby/{id}", id);
			statusCode = response.getStatusCode();
			assertEquals(405, statusCode);
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}


	}
}
