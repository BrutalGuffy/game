package gs;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class GameRepository {
    private static ConcurrentHashMap<Long, GameSession> map = new ConcurrentHashMap<>();

    public static void put(GameSession session) {
        map.put(session.getId(), session);
    }

    public static Collection<GameSession> getAll() {
        return map.values();
    }
}