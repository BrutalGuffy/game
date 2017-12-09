package gs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameServer implements Runnable {
    private static final Logger log = LogManager.getLogger(GameServer.class);

    @Override
    public void run() {
        log.info("GS started");
    }

}
