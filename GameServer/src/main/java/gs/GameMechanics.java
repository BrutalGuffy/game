package gs;

import Client.Action;
import geometry.Point;
import objects.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

public class GameMechanics implements Tickable {
    private ArrayList<Action> lastActions = new ArrayList<>();

    public GameMechanics () {
        lastActions.ensureCapacity(8);
    }

    public void mechanic(Set<Tickable> tickables) {
        for (Action action: InputQueue.getInstance()) {
            if (Objects.equals(action.getTopicDto().getTopic(), "PLANT_BOMB")) {
                lastActions.add(action.getId() - 1, action);
                continue;
            }
            lastActions.add(action.getId() + 3, action);
        }

        InputQueue.getInstance().clear();

        for (Action action: lastActions) {
            if (action == null) {
                break;
            }
            for (Tickable tick: tickables) {
                if (tick instanceof Bomb && ((Bomb) tick).getLifeTime() == 0) {
                    //some mechanic to destroy
                    tickables.remove(tick);
                }
                if (tick instanceof Bomb && Objects.equals(action.getTopicDto().getTopic(), "PLANT_BOMB")) {
                    for (Tickable ticks: tickables) {
                        if (ticks instanceof Player && Objects.equals(((Player) ticks).getId(), action.getId())) {
                            Point point = new Point(((Player) ticks).getPosition().getX(), ((Player) ticks).getPosition().getY());
                            Bomb bomb = new Bomb(point);
                            tickables.add(bomb);
                        }
                    }
                }
                if (tick instanceof Player && Objects.equals(action.getTopicDto().getTopic(), "MOVE")) {
                    Player player = (Player)tick;
                    player.setSpeed(0.05);
                    player.setDirection(action.getTopicDto().getData().getDirection());
                }
            }
        }

    }

    @Override
    public void tick(long elapsed) {
    }
}
