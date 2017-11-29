package jUnit;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import mm.ConnectionQueue;
import mm.Connection;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Ignore
public class ConnectionQueueTest {

    @Before
    public void setUp() throws Exception {
        ConnectionQueue.getInstance().offer(new Connection(1, "name1"));
        ConnectionQueue.getInstance().offer(new Connection(2, "name2"));
        ConnectionQueue.getInstance().offer(new Connection(3, "name3"));
    }

    @Test
    public void QueueTest() throws Exception {
        assertEquals(ConnectionQueue.getInstance().poll().getName(), "name1");
        assertEquals(ConnectionQueue.getInstance().poll().getPlayerId(), 2);
        assertEquals(ConnectionQueue.getInstance().size(), 1);
        assertEquals(ConnectionQueue.getInstance().poll().toString(), "Connection{playerId=3, name='name3'}");
    }
}