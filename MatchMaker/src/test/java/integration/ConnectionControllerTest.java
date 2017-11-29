package integration;

import mm.ConnectionController;
import mm.HttpClient;
import mm.MatchMaker;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Ignore
public class ConnectionControllerTest {
    private HttpClient httpCl = new HttpClient();

    @Before
    public void setUp() throws Exception {
        ConnectionController.set_gameId("5");
        MatchMaker.set_GameId("5");
        MatchMaker.set_players_in_game(2);
    }

    @Test
    public void returnedId() throws Exception {
        String gameId = httpCl.post("http://localhost:8090/matchmaker/join", "name");
        assertEquals(gameId, "5");
    }
}