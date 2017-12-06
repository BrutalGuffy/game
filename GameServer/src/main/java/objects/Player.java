package objects;

import geometry.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Player implements GameObject, Positionable, Movable, Tickable {
    private int id;
    private double speed;
    private String direction;
    private Point position;
    private static final Logger log = LogManager.getLogger(Player.class);

    public Player(int id) {
        this.id = id;
        this.position = new Point(0, 0);
        log.info("new Player has been created");
    }

    public Player(int id, Point p) {
        this.id = id;
        this.position = new Point(p.getX(), p.getY());
        log.info("new Player has been created");
    }

    public void setPosition(Point point) {
        position = point;
    }

    public void setSpeed(Double spd) {
        speed = spd;
    }

    public void setDirection(String direct) {
        direction = direct;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public Point getPosition() {
        return this.position;
    }

    @Override
    public void tick(long elapsed) {
        move(direction, elapsed);
    }

    @Override
    public Point move(String direction, long time) {
            if (Objects.equals(direction, "UP")) {
                setPosition(new Point(getPosition().getX(), getPosition().getY() + 1));
            }
            if (Objects.equals(direction, "DOWN")) {
                setPosition(new Point(getPosition().getX(), getPosition().getY() - 1));
            }
            if (Objects.equals(direction, "LEFT")) {
                setPosition(new Point(getPosition().getX() - 1, getPosition().getY()));
            }
            if (Objects.equals(direction, "RIGHT")) {
                setPosition(new Point(getPosition().getX() + 1, getPosition().getY()));
            }
        return getPosition();
        }

}

