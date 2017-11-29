package integration;

import mm.Connection;
import mm.ConnectionQueue;
import mm.MatchMaker;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MatchMakerTest {

    @Before
    public void setUp() throws Exception {

    }

    //have to start GS before
    @Test
    public void returnedId() throws Exception {
        Thread matchMaker = new Thread(new MatchMaker());
        matchMaker.start();
        ConnectionQueue.getInstance().offer(new Connection(1, "name1"));
        Thread.sleep(3000);
        assertEquals(MatchMaker.get_gameId(), "0");
        assertEquals(MatchMaker.get_players_in_game(), 1);

        Thread.sleep(3000);
        ConnectionQueue.getInstance().offer(new Connection(2, "name2"));
        assertEquals(MatchMaker.get_gameId(), "0");
        assertEquals(MatchMaker.get_players_in_game(), 2);
    }
}