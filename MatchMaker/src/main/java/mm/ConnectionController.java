package mm;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class ConnectionController {
    private static final Logger log = LogManager.getLogger(ConnectionController.class);
    private static AtomicLong id = new AtomicLong();
    public static String GameId = "-1";

    @Autowired
    private GameService gameService;

    @RequestMapping(
            path = "matchmaker/join",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String connect(@RequestBody String name, HttpServletResponse response) throws InterruptedException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        long newId = id.getAndIncrement();
        String[] parts = name.split("=");
        ConnectionQueue.getInstance().offer(new Connection(newId, parts[1]));

        log.info("New connection LOGIN={} ID={}", parts[1],newId);
        while (GameId == "-1") {   //have to fix this shit
           log.info("Waiting for MatchMaker response",GameId);
        }
        //<-----MatchMaker make POST REQUEST to GS and return id to GameId--->
        log.info("MM returned to player Login={} GameId={}", name, GameId);
        gameService.login(parts[1]);
        gameService.login(parts[1]);
        return GameId;
    }

    public static void set_GameId(String Game_Id) {
        GameId = Game_Id;
    }
}

