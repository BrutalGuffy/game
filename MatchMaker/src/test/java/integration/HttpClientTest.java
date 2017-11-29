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
public class HttpClientTest {
    private HttpClient httpCl = new HttpClient();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void postRequest() throws Exception {
        ConnectionController.set_gameId("0");
        MatchMaker.set_GameId("0");
        String gameId = httpCl.post("http://localhost:8090/matchmaker/join", "name=2");
        assertEquals(gameId, "0");
    }
}