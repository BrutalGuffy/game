package dto;

import geometry.Point;

public class PawnDto {
    private Point position;
    private int id;
    private double velocity;
    private String type;

    public void set_position(Point point) {
        position = point;
    }

    public void set_id(int player_id) {
        id = player_id;
    }

    public void set_double(double player_velocity) {
        velocity = player_velocity;
    }

    public void set_type() {
        type = "Pawn";
    }
}
