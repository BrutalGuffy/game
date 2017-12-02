package mm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MatchMakerApp {
    public static void main(String[] args) throws Exception {
        Thread matchMaker = new Thread(new MatchMaker());
        matchMaker.start();
        SpringApplication.run(MatchMakerApp.class, args);
    }
}
