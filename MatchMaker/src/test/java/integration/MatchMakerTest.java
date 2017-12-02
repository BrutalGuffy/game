package integration;

import mm.Connection;
import mm.ConnectionQueue;
import mm.MatchMaker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;


import static org.junit.Assert.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;


public class MatchMakerTest {

    private ClientAndServer mockServer;

    @Before
    public void setUp() throws Exception {
        mockServer = mockServer.startClientAndServer(8080);

        new MockServerClient("localhost", 8080)
                .when(request().withMethod("POST").withPath("/game/create")
                        .withBody("4").withHeader("\"Content-type\", \"text/plain\""))
                .respond(response().withHeaders(new Header("Content-Type", "text/plain"))
                        .withBody("0"));

        new MockServerClient("localhost", 8080)
                .when(request().withMethod("POST").withPath("/game/start")
                        .withBody("0").withHeader("\"Content-type\", \"text/plain\""))
                .respond(response().withHeaders(new Header("Content-Type", "text/plain"))
                        .withBody("0"));

        Thread matchMaker = new Thread(new MatchMaker());
        matchMaker.start();
    }

    @Test
    public void returnedId() throws Exception {
        ConnectionQueue.getInstance().offer(new Connection(1, "name1"));
        Thread.sleep(3000);
        assertEquals(MatchMaker.get_gameId(), "0");
        assertEquals(MatchMaker.get_players_in_game(), 1);

        ConnectionQueue.getInstance().offer(new Connection(2, "name2"));
        Thread.sleep(1000);
        assertEquals(MatchMaker.get_gameId(), "0");
        assertEquals(MatchMaker.get_players_in_game(), 2);

        ConnectionQueue.getInstance().offer(new Connection(3, "name3"));
        Thread.sleep(1000);
        assertEquals(MatchMaker.get_gameId(), "0");
        assertEquals(MatchMaker.get_players_in_game(), 3);

        ConnectionQueue.getInstance().offer(new Connection(4, "name4"));
        Thread.sleep(1000);
        assertEquals(MatchMaker.get_gameId(), "-1");
        assertEquals(MatchMaker.get_players_in_game(), 0);
    }

    @After
    public void stop() throws Exception {
        mockServer.stop();
    }
}