package objects;

import dto.PawnDto;
import geometry.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Player implements GameObject, Positionable, Movable, Tickable {
    private int id;
    private static AtomicInteger intId = new AtomicInteger();
    private String name;
    private double speed;
    private String direction;
    private Point position;
    private final Logger log = LogManager.getLogger(Player.class);

    public Player(String name_) {
        name = name_;
        speed = 0.4;
        id = intId.incrementAndGet();
        this.position = new Point(32, 32);
        log.info("new Player has been created");
    }

    public Player(String name_, Point p) {
        name = name_;
        id = intId.incrementAndGet();
        position = new Point(p.getX(), p.getY());
        log.info("new Player has been created");
    }

    public void setName(String name_) { name = name_; }
    public void setPosition(Point point) {
        position = point;
    }
    public void setSpeed(Double spd) {
        speed = spd;
    }
    public void setDirection(String direct) {
        direction = direct;
    }

    public double getSpeed() {
        return speed;
    }
    public String getName() {
        return name;
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
                position.setY(getPosition().getY() + speed*10);
            }
            if (Objects.equals(direction, "DOWN")) {
                position.setY(getPosition().getY() - speed*10);
            }
            if (Objects.equals(direction, "LEFT")) {
                position.setX(getPosition().getX() - speed*10);
            }
            if (Objects.equals(direction, "RIGHT")) {
                position.setX(getPosition().getX() + speed * 10);
            }
        return getPosition();
        }

    public Point moveBack() {
        if (Objects.equals(direction, "UP")) {
            position.setY(getPosition().getY() - speed*10);
        }
        if (Objects.equals(direction, "DOWN")) {
            position.setY(getPosition().getY() + speed*10);
        }
        if (Objects.equals(direction, "LEFT")) {
            position.setX(getPosition().getX() + speed*10);
        }
        if (Objects.equals(direction, "RIGHT")) {
            position.setX(getPosition().getX() - speed*10);
        }
        return getPosition();
    }
}

