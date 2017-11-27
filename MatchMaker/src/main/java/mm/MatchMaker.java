package mm;

import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MatchMaker implements Runnable {
    private static final Logger log = LogManager.getLogger(MatchMaker.class);
    private static String GameId = "-1";
    private static int Players_in_game = 0;
    private static final int PLAYERS_NEED = 4;

    @Override
    public void run() {
        log.info("Started");
        ConnectionController conCon = new ConnectionController();
        while (!Thread.currentThread().isInterrupted()) {
            if (this.GameId == "-1" && ConnectionQueue.getInstance().size() == 1) {
                //POST REQUEST TO 8090/create/game---->
                HttpClient httpCl = new HttpClient();
                try {
                    //GameId = httpCl.post("http://localhost:8080/create/game", ConnectionQueue.getInstance().poll().toString());
                    GameId = httpCl.post("http://localhost:8080/game/create", Integer.toString(PLAYERS_NEED));
                    log.info("GS returned Game_Id={}",GameId);
                    ConnectionController.set_GameId(GameId);
                    this.Players_in_game++;
                    ConnectionQueue.getInstance().poll();
                    log.info("First player joined");

                } catch (Exception e) {
                    log.info("Failed to connect to GS");
                    e.printStackTrace();
                }
            }
            if (this.GameId != "-1" && ConnectionQueue.getInstance().size() == 1) {
                this.Players_in_game++;
                log.info("One more player in game. Now players={}", Players_in_game);
                ConnectionQueue.getInstance().poll();
            }
            if (Players_in_game == 4) {
                //POST REQUEST TO 8090/start/game---->
                log.info("All players in game. Start game.");
                HttpClient httpCl = new HttpClient();
                try {
                    //GameId = httpCl.post("http://localhost:8080/create/game", ConnectionQueue.getInstance().poll().toString());
                    String Game_start = httpCl.post("http://localhost:8080/game/start", GameId);
                    log.info("Starting game id={}", GameId);
                } catch (Exception e) {
                    log.info("Failed to start this game");
                    e.printStackTrace();
                }
                set_GameId("-1");
                ConnectionController.set_GameId("-1");
                set_Players_in_game(0);
            }
        }
    }

    public static String get_GameId() {
        return GameId;
    }
    public static void set_GameId(String Game_Id) {
        GameId = Game_Id;
    }
    public static void set_Players_in_game(int n) {
        Players_in_game = n;
    }
}
