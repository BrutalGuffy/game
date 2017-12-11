package gamemechanic;

import client.Action;
import client.Message;
import client.Topic;
import gamesession.GameSession;
import geometry.Bar;
import geometry.Point;
import objects.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

public class GameMechanics implements Tickable {
    private ArrayList<Action> lastActions = new ArrayList<>();

    public void changeTickables(Set<Tickable> tickables) {
        for (Action action: lastActions) {
            if (action == null) {
                continue;
            }
            for (Player player: GameSession.getMapPlayers()) {
                if (Objects.equals(player.getName(), action.getName())) {
                    if (Objects.equals(action.getMessage().getTopic(), Topic.MOVE)) {
                        player.setDirection(action.getMessage().getData().getDirection());
                    }
                    if (Objects.equals(action.getMessage().getTopic(), Topic.PLANT_BOMB)) {
                        GameSession.addBomb(new Bomb(closesPoint(player.getPosition())));
                    }
                }
            }
        }
        lastActions.clear();
    }

    public void readQueue () {
        while (lastActions.size() < 8) {
            lastActions.add(null);
        }
        for (Action action: InputQueue.getInstance()) {
            if (Objects.equals(action.getMessage().getTopic().toString(), "PLANT_BOMB")) {
                lastActions.add(GameSession.getPlayerId(action.getName()) - 1, action);
                continue;
            }
            lastActions.add(GameSession.getPlayerId(action.getName()) + 3, action);
        }
        InputQueue.getInstance().clear();
    }

    public void checkCollisions() {
        for (Player player: GameSession.getMapPlayers()) {
            Bar barPlayer = new Bar(player);
            for (Wall wall: GameSession.getMapWalls()) {
                Bar barWall = new Bar(wall);
                if (barPlayer.isColliding(barWall)) {
                    player.moveBack();
                }
            }
            for (Wood wood: GameSession.getMapWoods()) {
                Bar barWood = new Bar(wood);
                if (barPlayer.isColliding(barWood)) {
                    player.moveBack();
                }
            }
            player.setDirection("IDLE");
        }
    }

    public Point closesPoint(Point point) {
        double modX = point.getX() % GameObject.width;
        double modY = point.getY() % GameObject.height;
        int divX = (int) point.getX() / (int) GameObject.width;
        int divY = (int) point.getY() / (int) GameObject.height;
        if (modX < 16) {
            if (modY < 16)
                return new Point(divX * GameObject.width, divY * GameObject.height);
            else
                return new Point(divX * GameObject.width, (divY + 1) * GameObject.height);
        }
        else if (modY < 16)
            return new Point((divX + 1) * GameObject.width, divY * GameObject.height);
        return new Point((divX + 1) * GameObject.width, (divY + 1) * GameObject.height);
    }

    public void detonationBomb() {
        ArrayList<Bomb> bombList = new ArrayList<>();
        ArrayList<Wood> woodList = new ArrayList<>();
        ArrayList<Player> playerList = new ArrayList<>();

        boolean horizontalRight = true;
        boolean verticalUp = true;
        boolean horizontalLeft = true;
        boolean verticalDown = true;

        for (Bomb bomb: GameSession.getMapBombs()) {
            if (bomb.getLifeTime() <= 0) {
                bombList.add(bomb);

                Point pointCurrent = new Point(bomb.getPosition().getX(), bomb.getPosition().getY());

                Point pointHorizontalRight1 = new Point(bomb.getPosition().getX()+ GameObject.width,
                        bomb.getPosition().getY());
                Point pointHorizontalRight2 = new Point(bomb.getPosition().getX()+ GameObject.width*2,
                        bomb.getPosition().getY());
                Point pointHorizontalLeft1 = new Point(bomb.getPosition().getX() - GameObject.width,
                        bomb.getPosition().getY());
                Point pointHorizontalLeft2 = new Point(bomb.getPosition().getX()- GameObject.width*2,
                        bomb.getPosition().getY());

                Point pointVerticalUp1 = new Point(bomb.getPosition().getX(),
                        bomb.getPosition().getY() + GameObject.height);
                Point pointVerticalUp2 = new Point(bomb.getPosition().getX(),
                        bomb.getPosition().getY() + GameObject.height*2);
                Point pointVerticalDown1 = new Point(bomb.getPosition().getX(),
                        bomb.getPosition().getY() - GameObject.height);
                Point pointVerticalDown2 = new Point(bomb.getPosition().getX(),
                        bomb.getPosition().getY() - GameObject.height*2);
;

                Bar barBombVerticalUp1 = new Bar(pointVerticalUp1);
                Bar barBombVerticalUp2 = new Bar(pointVerticalUp2);
                Bar barBombHorizontalRight1 = new Bar(pointHorizontalRight1);
                Bar barBombHorizontalRight2 = new Bar(pointHorizontalRight2);

                Bar barBombVerticalDown1 = new Bar(pointVerticalDown1);
                Bar barBombVerticalDown2 = new Bar(pointVerticalDown2);
                Bar barBombHorizontalLeft1 = new Bar(pointHorizontalLeft1);
                Bar barBombHorizontalLeft2 = new Bar(pointHorizontalLeft2);

                for (Wall wall: GameSession.getMapWalls()) {
                    Bar barWall = new Bar(wall);
                    if (barWall.isColliding(barBombHorizontalRight1)) {
                        horizontalRight = false;
                    }
                    if (barWall.isColliding(barBombVerticalUp1)) {
                        verticalUp = false;
                    }
                    if (barWall.isColliding(barBombHorizontalLeft1)) {
                        horizontalLeft = false;
                    }
                    if (barWall.isColliding(barBombVerticalDown1)) {
                        verticalDown = false;
                    }
                }

                for (Wood wood: GameSession.getMapWoods()) {
                    Bar barWood = new Bar(wood);
                    if (verticalUp && (barWood.isColliding(barBombVerticalUp2)
                            || barWood.isColliding(barBombVerticalUp1))) {
                        woodList.add(wood);
                        continue;
                    }
                    if (horizontalRight && (barWood.isColliding(barBombHorizontalRight2)
                            || barWood.isColliding(barBombHorizontalRight1))) {
                        woodList.add(wood);
                        continue;
                    }
                    if (verticalDown && (barWood.isColliding(barBombVerticalDown2)
                            || barWood.isColliding(barBombVerticalDown1))) {
                        woodList.add(wood);
                        continue;
                    }
                    if (horizontalLeft && (barWood.isColliding(barBombHorizontalLeft2)
                            || barWood.isColliding(barBombHorizontalLeft1))) {
                        woodList.add(wood);
                    }
                }
                for (Player player: GameSession.getMapPlayers()) {
                    Bar barPlayer = new Bar(player);
                    if (verticalUp && (barPlayer.isColliding(barBombVerticalUp2)
                            || barPlayer.isColliding(barBombVerticalUp1))) {
                        playerList.add(player);
                        continue;
                    }
                    if (horizontalRight && (barPlayer.isColliding(barBombHorizontalRight2)
                            || barPlayer.isColliding(barBombHorizontalRight1))) {
                        playerList.add(player);
                        continue;
                    }
                    if (verticalDown && (barPlayer.isColliding(barBombVerticalDown2)
                            || barPlayer.isColliding(barBombVerticalDown1))) {
                        playerList.add(player);
                        continue;
                    }
                    if (horizontalLeft && (barPlayer.isColliding(barBombHorizontalLeft2)
                            || barPlayer.isColliding(barBombHorizontalLeft1))) {
                        playerList.add(player);
                    }
                }
            }
        }

        for (Bomb bomb: bombList) {
            GameSession.getMapFire().add(new Fire(bomb.getPosition()));
            if (verticalUp) {
                Point point = new Point(bomb.getPosition().getX(),
                        bomb.getPosition().getY() + GameObject.height);
                GameSession.getMapFire().add(new Fire(point));
                point.setY(bomb.getPosition().getY() + GameObject.height*2);
                GameSession.getMapFire().add(new Fire(point));
            }
            if (verticalDown) {
                Point point = new Point(bomb.getPosition().getX(),
                        bomb.getPosition().getY() - GameObject.height);
                GameSession.getMapFire().add(new Fire(point));
                point.setY(bomb.getPosition().getY() - GameObject.height*2);
                GameSession.getMapFire().add(new Fire(point));
            }

            if (horizontalRight) {
                Point point = new Point(bomb.getPosition().getX() + GameObject.width,
                        bomb.getPosition().getY());
                GameSession.getMapFire().add(new Fire(point));
                point.setX(bomb.getPosition().getX() + GameObject.width*2);
                GameSession.getMapFire().add(new Fire(point));
            }
            if (horizontalLeft) {
                Point point = new Point(bomb.getPosition().getX() - GameObject.width,
                        bomb.getPosition().getY());
                GameSession.getMapFire().add(new Fire(point));
                point.setX(bomb.getPosition().getX() - GameObject.width*2);
                GameSession.getMapFire().add(new Fire(point));
            }
            GameSession.getMapBombs().remove(bomb);
        }

        for (Wood wood: woodList) {
            GameSession.getMapWoods().remove(wood);
        }
        for (Player player: playerList) {
            GameSession.getMapPlayers().remove(player);
        }
    }

    @Override
    public void tick(long elapsed) {
        checkCollisions();
        detonationBomb();
    }
}
