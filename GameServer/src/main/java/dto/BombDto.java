package dto;

import geometry.Point;

public class BombDto {
    private Point position;
    private int id;
    private String type;

    public void set_position(Point point) {
        position = point;
    }

    public void set_id(int bomb_id) {
        id = bomb_id;
    }

    public void set_type() {
        type = "Bomb";
    }
}
