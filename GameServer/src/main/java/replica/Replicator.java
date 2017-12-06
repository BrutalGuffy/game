package replica;

import com.google.gson.Gson;
import dto.TopicDto;

import java.util.ArrayList;

public class Replicator {
    private static final Gson gson = new Gson();

    public static String replica(ArrayList<Object> objects) {
        return gson.toJson(objects);
    }

    public static String replica(Object object) {
        return gson.toJson(object);
    }

    public static TopicDto topic(String json) {
        return gson.fromJson(json, TopicDto.class);
    }
}
