package gs;

import com.google.gson.Gson;
import dto.BombDto;
import dto.DataDto;
import dto.TopicDto;
import geometry.Point;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import replica.Replicator;

import java.util.ArrayList;


//@SpringBootApplication
public class GameServerApp {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(GameServerApp.class, args);
        Thread gameServer = new Thread(new GameServer());
        gameServer.start();
        //Ticker ticker = new Ticker();
        //ticker.gameLoop();
        /*ArrayList<Object> replica = new ArrayList<>();
        BombDto bombDto = new BombDto();
        bombDto.set_id(1);
        Point point = new Point(3,4);
        bombDto.set_position(point);
        replica.add(bombDto);
        replica.add(bombDto);
        Gson gson = new Gson();
        String str = gson.toJson(bombDto);
        System.out.println(str);
        String json = "{\"topic\":\"PLANT_BOMB\",   \"data\": {}}";
        TopicDto topicDto = new TopicDto();
        DataDto dataDto = new DataDto();
        dataDto.setDirection("UP");
        topicDto.set_topic("MOVE");
        topicDto.set_data(dataDto);
        replica.add(topicDto);
        String res = Replicator.replica(topicDto);
        System.out.println(res);
        topicDto = Replicator.topic(json);
        System.out.println(topicDto.getTopic());
        System.out.println(topicDto.getData().getDirection());*/
    }
}
