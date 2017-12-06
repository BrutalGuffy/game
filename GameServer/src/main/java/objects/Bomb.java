package objects;

import org.apache.logging.log4j.LogManager;
import geometry.Point;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.concurrent.atomic.AtomicLong;
public class Bomb implements GameObject, Positionable, Tickable {
    /*
    * Bomb, will be destroy after allTime
    */
    private AtomicLong idGenerator = new AtomicLong();
    private int id = (int) idGenerator.getAndIncrement();
    private static final double allTime = 2000;
    private double lifeTime;
    private Point position;
    private static final Logger log = LogManager.getLogger(Player.class);

    public Bomb(Point p) {
        this.position = new Point(p.getX(), p.getY());
        log.info("new Bomb id = {} has been planted", id);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Point getPosition() {
        return this.position;
    }

    public double getLifeTime() { return lifeTime; }

    public void setPosition(Point point) {
        position = point;
    }

    public void setLifeTime(double time) { lifeTime = time; }

    @Override
    public void tick(long elapsed){
        lifeTime -= elapsed;
    }
}

