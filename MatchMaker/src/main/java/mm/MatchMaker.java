package mm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MatchMaker implements Runnable {
    private static final Logger log = LogManager.getLogger(MatchMaker.class);
    private static String gameId = "-1";
    private static int players_in_game = 0;
    private static final int PLAYERS_NEED = 4;

    @Override
    public void run() {
        log.info("Started");
        ConnectionController conCon = new ConnectionController();
        while (!Thread.currentThread().isInterrupted()) {
            if (this.gameId == "-1" && ConnectionQueue.getInstance().size() == 1) {
                //POST REQUEST TO 8090/create/game---->
                HttpClient httpCl = new HttpClient();
                try {
                    gameId = httpCl.post("http://localhost:8080/game/create", Integer.toString(PLAYERS_NEED));
                    log.info("GS returned Game_Id={}",gameId);
                    ConnectionController.set_gameId(gameId);
                    this.players_in_game++;
                    ConnectionQueue.getInstance().poll();
                    log.info("First player joined");

                } catch (Exception e) {
                    log.info("Failed to connect to GS");
                    e.printStackTrace();
                }
            }
            if (this.gameId != "-1" && ConnectionQueue.getInstance().size() == 1) {
                this.players_in_game++;
                log.info("One more player in game. Now players={}", players_in_game);
                ConnectionQueue.getInstance().poll();
            }
            if (players_in_game == 4) {
                //POST REQUEST TO 8090/start/game---->
                log.info("All players in game. Start game.");
                HttpClient httpCl = new HttpClient();
                try {
                    String gameStart = httpCl.post("http://localhost:8080/game/start", gameId);
                    log.info("Starting game id={}", gameStart);
                } catch (Exception e) {
                    log.info("Failed to start this game");
                    e.printStackTrace();
                }
                set_GameId("-1");
                ConnectionController.set_gameId("-1");
                set_players_in_game(0);
            }
        }
    }

    public static String get_gameId() {
        return gameId;
    }

    public static void set_GameId(String gameid) {
        gameId = gameid;
    }

    public static void set_players_in_game(int n) {
        players_in_game = n;
    }
}
