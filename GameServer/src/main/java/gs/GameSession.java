package gs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

public class GameSession {
    private static final Logger log = LogManager.getLogger(GameServer.class);
    private static AtomicLong idGenerator = new AtomicLong();

    public static final int PLAYERS_IN_GAME = 4;

    //private final Connection[] connections;
    private static final long id = idGenerator.getAndIncrement();

    /*public GameSession(Connection[] connections) {
        this.connections = connections;
    }

    @Override
    public String toString() {
        return "GameSession{" + ", id=" + id +
                "connections=" + Arrays.toString(connections) +
                '}';
    }*/

    public static long getId() {
        return id;
    }
}
